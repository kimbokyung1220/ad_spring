package com.example.demo.config;

import com.example.demo.config.jwt.JwtAccessDeniedHandler;
import com.example.demo.config.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring Security 5.7.0-M2 부터 해당 클래스는 컴포넌트 기반의 보안 설정을 권장한다는 이유로 WebSecurityConfigurerAdapter => Deprecated
 * ~ 5.7.0) @Override 후 재정의
 * 5.7.0 ~) @Bean으로 등록 [SecurityFilterChain, WebSecurityCustomizer]
 */

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * 비밀번호 암호화
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * [ WebSecurityCustomizer ]
     * 사용자가 정적 자원에 대한 접근에 대해 ‘인가’에 대한 설정을 담당하는 메서드
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/h2-console/**", "/favicon.ico") // h2 database 테스트가 원활하도록 관련 API 들은 전부 무시
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()) //정적 파일(css, 이미지, ... 등)
                ;
    }

    /**
     * [ CorsConfigurationSource ]
     * 스프링 부트에서 CORS를 해결하는 방법 중 하나
     */
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // origin
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // ip응답에 허용
        configuration.addAllowedOrigin("http://localhost:3000");
        // 내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정 => false: 요청안옴
        configuration.setAllowCredentials(true);
        // 모든 header에 응답을 허용
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 모든 method에 응답을 허용
        configuration.setAllowedMethods(Arrays.asList("*"));
        //configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        // 노출시킬때 헤더 (JWT)
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh-Token", "Access_Control-Allow-Origin"));
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     * [ SecurityFilterChain ]
     *  HTTP에 대해서 ‘인증’과 ‘인가’를 담당하는 메서드이며 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http.csrf().disable()

                // exception handling 할 때 우리가 만든 클래스를 추가
                .exceptionHandling()
                // 인증실패시 AuthenticationException을 호출 => AuthenticationEntryPoint 인터페이스로 커스텀 가능
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 인가실패시 처리 AccessDeniedException을 호출 => AccessDeniedHandler 인터페이스로 커스텀 가능
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                // cors 관련
                .cors().configurationSource(corsConfigurationSource())

                // 동일 도메인에서는 iframe접근이 가능하도록 X-Frame-Options를 same으로 설정
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .and()
                // URL별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                .antMatchers("/", "/**").permitAll()
                .antMatchers(HttpMethod.POST,"/common/login").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/chart").hasRole("ADMIN")
//                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요
                .anyRequest().permitAll()   // 나머지 API 는 전부 인증 필요

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
