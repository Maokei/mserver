package se.maokei.mserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = EmbeddedMongoAutoConfiguration.class)
public class MserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MserverApplication.class, args);
	}
}
