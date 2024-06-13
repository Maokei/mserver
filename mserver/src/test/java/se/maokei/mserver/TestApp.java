package se.maokei.mserver;

import org.springframework.boot.SpringApplication;

public class TestApp {

    public static void main(String[] args) {
        SpringApplication.from(MserverApplication::main).with(TestcontainersConfiguration.class).run(args);
    }
}
