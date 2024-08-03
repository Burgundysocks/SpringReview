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
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(csrf -> csrf.disable())
                // 프레임 옵션 비활성화
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // URL 접근 권한 설정
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                              "/",
                                "/css/**",
                                "/images/**",
                                "/js/**"
                        ).permitAll()  // 모든 사용자에게 허용
                        .requestMatchers("/api/**").hasRole(Role.GUEST.name())  // GUEST 역할만 허용
                        .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                // OAuth2 로그인 설정
                .oauth2Login(oauth2Login -> oauth2Login
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(customOAuth2UserService)
                        )
                );

        return http.build();
    }
}
