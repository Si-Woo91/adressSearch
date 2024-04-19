# 한영 주소 찾기 서비스

# 📖 목차

1. [프로젝트 소개](#-프로젝트-소개)
2. [기술스택](#-기술스택)
3. [화면구성 및 기능](#%EF%B8%8F-화면구성-및-기능)
4. [트러블 슈팅](#-트러블-슈팅)

# 📃 프로젝트 소개

Spring Security와 소셜로그인 api, 주소검색 api를 활용한 한영 주소 검색 서비스입니다.



# 🚨 기술스택

### Environment
<img src="https://img.shields.io/badge/STS-6DB33F?style=for-the-badge&logo=STS&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/github-FC6D26?style=for-the-badge&logo=github&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/DBeaver-44b0a7?style=for-the-badge&logo=DBeaver&logoColor=white">&nbsp;

### FrontEnd
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS#&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/bootstrap-8224e3?style=for-the-badge&logo=bootstrap&logoColor=white">&nbsp;

### BackEnd
<img src="https://img.shields.io/badge/Java17-007396?style=for-the-badge&logo=Java17&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=Thymeleaf&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/Oracle-4479A1?style=for-the-badge&logo=Oracle&logoColor=white">

[목차🔺](#-목차)

# 🖥️ 화면구성 및 기능








# ✅ 트러블 슈팅

<details>

<summary>SecurityConfig 설정</summary>
<br>

### 현상 : ...deprecated and marked for removal 오류 발생

```java
    // 기존 코드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeRequests()    // 인증, 인가 설정
                .requestMatchers("/login", "/signup", "/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()    // 폼 기반 로그인 설정
                .loginPage("/login")
                .defaultSuccessUrl("/articles")
                .and()
                .logout()   // 로그아웃 설정
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()   // csrf 비활성화
                .build();

    }
```

```java
    // 변경 코드
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                        .requestMatchers("/adressSearch").hasAnyRole("USER")
                        		.requestMatchers("/**", "/signup", "/image/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                        		.loginPage("/")
                        		.failureHandler(authenticationFailureHandler)
                        		.usernameParameter("email")					//username으로 받을 값을 지정
                        		.defaultSuccessUrl("/adressSearch", true)	// 로그인 성공 후 이동할 페이지 설정
                )
                .logout((logoutConfig) ->
                		logoutConfig
                				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                				.logoutSuccessUrl("/")
                				.invalidateHttpSession(true)	// 로그아웃시 세션삭제
                )
    			.oauth2Login((oauth2) -> oauth2
                        .defaultSuccessUrl("/adressSearch")); // 성공 페이지로의 리다이렉션);

        return http.build();
    }
```

- 프로젝트를 하던 도중 웹 보안 설정을 위한 WebSecurityConfig 클래스 작성 중 작성한 filterChain 메소드에서 에러가 발생.
- 이유 : Spring Security 6.1.0 이상의 버전에서는 메서드 체이닝이 deprecated 되어 람다식을 통한 설정을 요구.
- 해결 방법 : 람다식으로 바꿔 오류 해결


</details>

<details>

<summary>소셜로그인이 아닌 일반회원가입 사용자 로그인시 아이디/비밀번호 이슈</summary>
<br>

### 현상 : 소셜로그인이 아닌 일반 회원가입 후 로그인을 할때 아이디/비밀번호가 올바르지 않다는 오류 내용을 출력해줌.

- 이유 : 로그인시 아래 로직의 파라미터가 들어오지 않고 있음. 기본적으로 시큐리티에서는 html에서 데이터 값을 username으로 보내줌. 하지만 나는 email로 보내주고 있었기에 파라미터가 들어오지 않고 있었음.

```java
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.debug("로그인 ID 확인 ::" + email);
		
		com.example.adress.domain.User userData = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("email(%s) not found".formatted(email)));
		
		return new User(userData.getEmail(), userData.getPassword(), userData.getAuthorities());
		
	}

}
```
-  해결방안 : SecurityConfig의 formLogin 부분에서 .usernameParameter("email")를 추가 해주었더니 정상적으로 동작.

</details>


[목차🔺](#-목차)
