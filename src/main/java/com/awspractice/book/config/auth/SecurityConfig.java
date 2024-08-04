package com.awspractice.book.config.auth;

import com.awspractice.book.domain.dto.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화해줍니다.
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())
                // 프레임 옵션 비활성화
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // URL 접근 권한 설정 authorizeRequests가 선언되어야만 Matchers옵선 사용 가능
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                              "/",
                                "/css/**",
                                "/images/**",
                                "/js/**"
                        ).permitAll()  // 모든 사용자에게 허용
                        .requestMatchers("/api/**").hasRole(Role.USER.name())  //api/** 주소를 가진 api는 USER만 사용할 수 잇도록 설정
                        .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessUrl("/") //로그아웃 성공시 ("/") 주소로 ㅇ동
                )
                // OAuth2 로그인 설정
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint //OAuth 로그인 성공 이후 사용자 정보를 가져올때의 설정 담당
                                .userService(customOAuth2UserService) //소셜로그인 성공 이후 후속조치를 진행할 Interface의 구현체 등록
                        )
                );

        return http.build();
    }
}
