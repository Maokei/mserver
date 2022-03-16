package se.maokei.mserver.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.maokei.mserver.dto.AuthRequest;
import se.maokei.mserver.dto.AuthResponse;
import se.maokei.mserver.security.JwtUtility;
import se.maokei.mserver.services.UserService;

@AllArgsConstructor
@RestController
public class AuthController {
  private JwtUtility jwtUtil;
  //private PBKDF2Encoder passwordEncoder;
  private PasswordEncoder passwordEncoder;
  private UserService userService;

  @PostMapping("/login")
  public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
    return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.matches(ar.getPassword(), userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
  }
}
