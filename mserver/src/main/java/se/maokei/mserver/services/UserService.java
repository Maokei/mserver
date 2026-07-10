package se.maokei.mserver.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import se.maokei.mserver.dto.UserRegisterDto;
import se.maokei.mserver.exception.UserAlreadyExistException;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;
import se.maokei.mserver.repository.UserRepository;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  /**
   * findByUsername
   * @param username User's username
   * */
  public Mono<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  /**
   * findByEmail
   * @param email User's email
   * */
  public Mono<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  /**
   * registerNewUser
   * @param userDto UserRegistrationDto
   * @return User or empty
   */
  public Mono<User> registerNewUser(UserRegisterDto userDto) throws RuntimeException {
    User newUser = User.builder()
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .email(userDto.getEmail())
            .roles(List.of(Role.ROLE_USER))
            .enabled(false)
            .build();
    return userRepository.findByEmail(userDto.getEmail()).flatMap(found -> {
      if(found != null) {
        LOGGER.error("User with email already exists {}", found.getEmail());
        return Mono.error(new UserAlreadyExistException("User with email already exists: " + found.getEmail()));
      }
      return Mono.just(newUser);
    }).switchIfEmpty(userRepository.save(newUser))
            .doOnError(Mono::error)
            .publishOn(Schedulers.boundedElastic())
            .doFinally(signal -> {
              if(signal.equals(SignalType.ON_COMPLETE)) {
                //TODO confirm user event -> send out email
                LOGGER.info("New user with email {} registered emit confirm event", newUser.getEmail());
              }
      userRepository.findAll().subscribe(System.out::println);
    });
  }

  /**
   * updateUserPassword
   * @param userId user id to lookup
   * @param user User pojo
   * */
  public Mono<User> updateUserPassword(UUID userId, User user) {
    return userRepository.findById(userId)
            .flatMap(dbUser -> {
              dbUser.setPassword(user.getPassword());
              return userRepository.save(dbUser);
            });
  }
}
