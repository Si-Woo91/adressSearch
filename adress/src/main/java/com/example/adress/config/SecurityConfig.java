package com.example.adress.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 시큐리티 관리 설정
public class SecurityConfig {
	
	private final AuthenticationFailureHandler authenticationFailureHandler;
	
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

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
