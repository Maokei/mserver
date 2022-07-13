package se.maokei.mserver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;
import se.maokei.mserver.repository.CommentRepository;
import se.maokei.mserver.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Slf4j
class DataInitializer implements CommandLineRunner {
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public void run(String[] args) {
    log.info("Starting data initialization  ...");
    User userOne = User.builder()
            .password(passwordEncoder.encode("password"))
            .username("admin")
            .roles(List.of(Role.ROLE_ADMIN))
            .build();
    userRepository.save(userOne);
  }

}