package se.maokei.mserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Role;
import se.maokei.mserver.model.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  private PasswordEncoder passwordEncoder;
  private Map<String, User> data;

  @PostConstruct
  public void init() {
    data = new HashMap<>();

    //username:password -> user:user
    data.put("user", new User(UUID.randomUUID(),"user", passwordEncoder.encode("password"), true, List.of(Role.ROLE_USER)));

    //username:password-> admin:admin
    data.put("admin", new User(UUID.randomUUID(),"admin", passwordEncoder.encode("password"), true, List.of(Role.ROLE_ADMIN)));
  }

  public Mono<User> findByUsername(String username) {
    System.out.println("findByUsername: " + username);
    return Mono.justOrEmpty(data.get(username));
  }
}
