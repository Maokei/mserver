package se.maokei.mserver.security;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class WebSecurityConfig {
  private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
  private AuthenticationManager authenticationManager;
  private SecurityContextRepository securityContextRepository;

  private static final String[] AUTH_WHITELIST = {
          "/swagger-resources",
          "/swagger-resources/**",
          "/configuration/ui",
          "/configuration/security",
          "/swagger-ui.html",
          "/webjars/**",
          "/v3/api-docs/**",
          "/api/public/**",
          "/api/public/authenticate",
          "/actuator/*",
          "/swagger-ui/**"
  };

  @Bean
  public PasswordEncoder encoder() {
    return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
  }

  @Bean
  public WebSecurityCustomizer ignoringCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/static/**", "/swagger-ui/**", "/v3/api-docs*/**");
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
      .exceptionHandling(Customizer.withDefaults())
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
      .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
      .authenticationManager(authenticationManager)
      .securityContextRepository(securityContextRepository)
      .build();
  }
}