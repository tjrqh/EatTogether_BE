package com.project.eatTogether.config;

import com.project.eatTogether.infrastructure.differed.MemberRepository;
import com.project.eatTogether.infrastructure.security.CustomUserDetailService;
import com.project.eatTogether.infrastructure.util.JWTUtil;
import com.project.eatTogether.infrastructure.util.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

  private final JWTUtil jwtUtil;
  private final CustomUserDetailService customUserDetailService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtil,
        customUserDetailService);

    http
        .csrf(AbstractHttpConfigurer::disable)  // CSRF 비활성화
        .cors(corsCustomizer -> corsCustomizer.configurationSource(
            corsConfigurationSource()))  // 여기를 수정
        .authorizeHttpRequests(auth -> auth
            // 누구나 접근 가능한 API
            .requestMatchers(HttpMethod.POST, "/api/member").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/member/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/mail").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/mail/check").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/member/refresh-token").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/member/signup/user").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/member/signup/owner").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/member/check-email").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/restaurants/categories").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/restaurants/cuisine-category").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/reservations").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/restaurants").permitAll()
            .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .requestMatchers("/api/payments/**").permitAll() // payment 엔드포인트 추가
            .requestMatchers("/api/payments/webhook").permitAll() // webhook 엔드포인트 추가
            .requestMatchers(HttpMethod.GET, "/api/restaurants/*/deposit-info").permitAll()
            .requestMatchers("/error").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/restaurant-details/**").permitAll()

            // 로그인한 사용자만 접근 가능한 API (주문, 장바구니, 회원 정보)
            .requestMatchers("/api/carts/**", "/api/orders/**").authenticated()
            .requestMatchers(HttpMethod.PUT, "/api/member/**").authenticated()
            .requestMatchers(HttpMethod.DELETE, "/api/member/**").authenticated()
            .requestMatchers(HttpMethod.GET, "/api/member/**").authenticated()
            // ADMIN 등급만 접근 가능한 API (상품 등록, 수정, 삭제)
            .requestMatchers(HttpMethod.POST, "/api/items").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/items/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/items/**").hasAuthority("ADMIN")
            // 그 외 요청은 인증 필요
            .anyRequest().authenticated()
        )
        .formLogin(AbstractHttpConfigurer::disable)  // 기본 로그인 폼 비활성화
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 세션 사용 안 함 (JWT 인증)
        .addFilterBefore(jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class)  // 인증 필터 추가
        .logout(AbstractHttpConfigurer::disable);  // 로그아웃 비활성화

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(
        Arrays.asList("http://127.0.0.1:5501", "http://localhost:3000"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList(
        "Authorization",
        "Content-Type",
        "Accept",
        "X-Requested-With",
        "Cache-Control"
    ));
    configuration.setExposedHeaders(Arrays.asList("Authorization"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();  // 비밀번호 암호화를 위한 BCryptPasswordEncoder 빈 등록
  }

  @Bean
  public UserDetailsService userDetailsService(MemberRepository memberRepository) {
    return new CustomUserDetailService(memberRepository);  // CustomUserDetailsService 빈 등록
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();  // AuthenticationManager 빈 등록
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(customUserDetailService);
    authProvider.setPasswordEncoder(bCryptPasswordEncoder());
    // 상세한 에러 메시지 활성화
    authProvider.setHideUserNotFoundExceptions(false);
    return authProvider;
  }

  @Configuration
  public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/**")
          .allowedOrigins("http://localhost:3000")  // React 앱 주소
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowedHeaders("*")
          .allowCredentials(true);
    }
  }
}

