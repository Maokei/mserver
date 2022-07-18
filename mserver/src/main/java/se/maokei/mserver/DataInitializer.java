package se.maokei.mserver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;
import se.maokei.mserver.repository.CommentRepository;
import se.maokei.mserver.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
class DataInitializer implements CommandLineRunner {
  private final UserRepository userRepository;
  private final CommentRepository commentRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public void run(String[] args) {
    log.info("Starting data initialization  ...");
    User userOne = User.builder()
            .password(passwordEncoder.encode("password"))
            .username("cat")
            .email("cat@gmail.com")
            .roles(List.of(Role.ROLE_ADMIN))
            .enabled(true)
            .build();
    userRepository.save(userOne).subscribe();
    userRepository.save(new User("user", passwordEncoder.encode("password"), "user@gmail.com",true, List.of(Role.ROLE_USER)))
        .subscribe();
    //username:password-> admin:admin
    userRepository.save(new User("admin", passwordEncoder.encode("password"), "admin@gmail.com",true, List.of(Role.ROLE_ADMIN)))
            .subscribe();
    userRepository.findAll().subscribe(System.out::println);
  }

}