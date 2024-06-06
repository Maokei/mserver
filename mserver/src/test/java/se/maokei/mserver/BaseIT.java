package se.maokei.mserver;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseIT {
  /*private static final MongoDBContainer MONGO_CONTAINER = new MongoDBContainer("mongo:latest")
      .withReuse(true);

  @DynamicPropertySource
  static void mongoProps(DynamicPropertyRegistry registry) {
    MONGO_CONTAINER.start();
    registry.add("spring.data.mongodb.uri", MONGO_CONTAINER::getReplicaSetUrl);
  }*/

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
  }
}
