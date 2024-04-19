# 한영 주소 찾기 서비스

# 📖 목차

1. [프로젝트 소개](#-프로젝트-소개)
2. [기술스택](#-기술스택)
3. [화면구성 및 기능](#%EF%B8%8F-화면구성-및-기능)
4. [트러블 슈팅](#-트러블-슈팅)


<br><br>

# 📃 프로젝트 소개

Spring Security와 소셜로그인 api, 주소검색 api를 활용한 한영 주소 검색 서비스입니다.
회원가입을 한 후 주소를 검색 할 수 있으며, 선택한 주소에 대하여 영문 주소까지 함께 볼 수 있는 서비스 입니다.


<br><br>

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


<br><br>

# 🖥️ 화면구성 및 기능

<table>
  <thead>
    <tr>
      <th style="text-align: center;">로그인 페이지</th>
      <th style="text-align: center;">로그인 실패</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center">
	      ![loginPage](https://github.com/Si-Woo91/adressSearch/assets/101760091/cf2c36dd-773f-407c-bdc5-26673772606a.jpg)
      </td>
      <td align="center">
	     ![loginFail](https://github.com/Si-Woo91/adressSearch/assets/101760091/6d7d9a1d-41d8-4470-b10d-e1c3c2293f79.jpg)
      </td>
    </tr>
  </tbody>
</table>

- 로그인

  - **인증 프로세스 구현**: Spring Security를 사용하여 사용자가 제공한 인증 정보(일반적으로 아이디와 비밀번호)를 검증, 이를 통해 사용자가 시스템에 접근 가능한지 확인
  - **비밀번호 해싱 및 매칭**: 사용자가 제공한 비밀번호를 Spring Security의 암호화 기능을 활용하여 저장된 해시된 비밀번호와 비교
  - **인가 및 권한 부여**: Spring Security를 사용하여 ROLE_USER 권한이 부여된 경우 해상 서비스를 이용 가능
  - **로그인 실패시 사유 alert으로 안내** : 실패시 CustomFailureHandler에 등록된 오류 내용으로 메시지 출력
  - **소셜로그인** : Kakao Api를 통해 카카오톡으로 로그인 회원가입 및 로그인이 가능
 
  
  </br></br>

  <table>
  <thead>
    <tr>
      <th style="text-align: center;">회원가입 페이지</th>
      <th style="text-align: center;">회원가입 실패</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center">
	     ![singupPage](https://github.com/Si-Woo91/adressSearch/assets/101760091/a5f096ad-e64d-46e6-af09-62b3c83c5a20.jpg)
      </td>
      <td align="center">
	     ![singupFail](https://github.com/Si-Woo91/adressSearch/assets/101760091/09d6ce5d-440e-4047-99ee-f26eb92625a0.jpg)
      </td>
    </tr>
  </tbody>
</table>

- 회원가입

 - **비밀번호 암호화 및 안전한 저장**: Spring Security의 암호화 기능을 이용하여 사용자의 비밀번호를 안전하게 해시화하고 데이터베이스에 저장.
 - **중복 확인**: 이미 등록된 사용자인지 확인하기 위해 이메일 주소를 기준으로 중복 여부를 확인


<br><br>

<table>
  <thead>
    <tr>
      <th style="text-align: center;">주소검색 페이지</th>
      <th style="text-align: center;">주소찾기 버튼 클릭시</th>
      <th style="text-align: center;">선택한 주소에 대한 내용 출력</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center">
	      ![adressPage](https://github.com/Si-Woo91/adressSearch/assets/101760091/8248734d-1549-41bb-a314-c0822a70a995.jpg)
      </td>
      <td align="center">
	     ![btnClick](https://github.com/Si-Woo91/adressSearch/assets/101760091/5a893a87-c4c7-4271-91c2-12c1b097a53e.jpg)
      </td>
      <td align="center">
	      ![adressSearch](https://github.com/Si-Woo91/adressSearch/assets/101760091/43a4c60d-80e7-4d5d-854b-19bb80003d3d.jpg)
      </td>
    </tr>
  </tbody>
</table>

- 주소찾기 서비스

  - **주소검색**: DAUM 주소 API를 이용한 주소 검색 및 한영 주소 출력


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
