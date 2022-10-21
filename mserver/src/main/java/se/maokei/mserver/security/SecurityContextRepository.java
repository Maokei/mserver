package se.maokei.mserver.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {
  private AuthenticationManager authenticationManager;

  @Override
  public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Mono<SecurityContext> load(ServerWebExchange swe) {
    final String token =  !swe.getRequest().getQueryParams().containsKey("token") ? swe.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION) : "Bearer " + swe.getRequest().getQueryParams().getFirst("token");
    return Mono.justOrEmpty(token)
            .filter(authHeader -> authHeader.startsWith("Bearer "))
            .flatMap(authHeader -> {
              String authToken = authHeader.substring(7);
              Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
              return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
            });
  }
}