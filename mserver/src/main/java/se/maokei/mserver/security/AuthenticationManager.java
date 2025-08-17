package se.maokei.mserver.security;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
  private static final Logger log = LoggerFactory.getLogger(AuthenticationManager.class);
  private JwtUtility jwtUtil;

  @Override
  @SuppressWarnings("unchecked")
  public Mono<Authentication> authenticate(Authentication authentication) {
    String authToken = authentication.getCredentials().toString();
    String username = jwtUtil.getUsernameFromToken(authToken);
    String userId = jwtUtil.getUserIdFromToken(authToken);

    return Mono.just(jwtUtil.validateToken(authToken))
        .filter(valid -> valid)
        .switchIfEmpty(Mono.empty())
        .map(valid -> {
            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
            List<String> rolesMap = claims.get("role", List.class);
            log.debug("Authenticated user: {}, id: {}, list of roles: {}, token:  {}", username, userId, rolesMap, authToken);
            return new CustomAuthentication(
                userId,
                username,
                authToken,
                rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
        });
  }
}
