package se.maokei.mserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;

@SpringBootApplication(exclude = EmbeddedMongoAutoConfiguration.class)
public class MserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MserverApplication.class, args);
	}
}
