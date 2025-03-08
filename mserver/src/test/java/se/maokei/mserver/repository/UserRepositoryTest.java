package se.maokei.mserver.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import se.maokei.mserver.TestcontainersConfiguration;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void before() {
        userRepository.deleteAll().subscribe();
    }

    @Test
    public void saveUserTest() {
        User user = new User("user1", passwordEncoder.encode("password"), "user@gmail.com", List.of(Role.ROLE_USER), true, true);

        Mono<Long> count = userRepository.save(user).then(userRepository.count());
        StepVerifier.create(count)
                .assertNext(
                        f -> {
                            Assertions.assertEquals(1, f, "Should be one user");
                        }
                )
                .verifyComplete();
    }

    @Test
    public void getUserTest() {
        User user = new User("user1", passwordEncoder.encode("password"), "user@gmail.com", List.of(Role.ROLE_USER), true, true);

        var persisted = userRepository.save(user)
                .then(userRepository.findByEmail(user.getEmail()));
        StepVerifier.create(persisted)
            .assertNext(
                f -> {
                    Assertions.assertEquals(user.getUsername(), f.getUsername(), "Usernames does not match");
                    Assertions.assertEquals(user.getRoles(), f.getRoles(), "Roles does not match");
                    Assertions.assertNotNull(f.getEmail(), "Email is missing");
                    Assertions.assertNotNull(f.getId(), "Id does not exist");
                }
            )
            .verifyComplete();
    }

    @Test
    public void updateUserTest() {
        User user = new User("user1", passwordEncoder.encode("password"), "user1@gmail.com", List.of(Role.ROLE_USER), true, true);
        String newEmail = "updated@gmail.com";

        var updated = userRepository.save(user).map(u -> {
            u.setEmail(newEmail);
            return u;
        }).flatMap((User u) -> userRepository.save(u));

        StepVerifier.create(updated)
        .assertNext(
            (User f) -> {
                    Assertions.assertEquals(user.getUsername(), f.getUsername(), "Usernames does not match");
                Assertions.assertEquals(List.of(Role.ROLE_USER), f.getRoles(), "Roles does not match");
                Assertions.assertNotNull(f.getEmail(), "Email is missing");
                Assertions.assertEquals(newEmail, f.getEmail(), "Email does not match");
                Assertions.assertNotNull(f.getId(), "Id does not exist");
                }
        )
        .verifyComplete();
    }

    @Test
    public void saveUserWithDuplicateEmailTest() {
        User user1 = new User("user1", passwordEncoder.encode("password"), "user1@gmail.com", List.of(Role.ROLE_USER), true, true);
        User user2 = new User("user2", passwordEncoder.encode("password"), "user1@gmail.com", List.of(Role.ROLE_USER), true, true);
        Mono<User> m1 = userRepository.save(user1).log();
        Mono<User> m2 = userRepository.save(user2).log();
        StepVerifier.create(Mono.zip(m1, m2).log()).expectError(DuplicateKeyException.class).verify();
    }
}
