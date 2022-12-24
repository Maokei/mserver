package se.maokei.mserver;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class BaseIT {
  private static final MongoDBContainer MONGO_CONTAINER = new MongoDBContainer("mongo:latest")
      .withReuse(true);

  @DynamicPropertySource
  static void mongoProps(DynamicPropertyRegistry registry) {
    MONGO_CONTAINER.start();
    registry.add("spring.data.mongodb.uri", MONGO_CONTAINER::getReplicaSetUrl);
  }
}
