package se.maokei.mserver.api.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import se.maokei.mserver.api.v1.handler.PingHandler;

@Configuration
public class PingRouter {
    @Bean
    public RouterFunction<ServerResponse> pingRoutes(PingHandler pingHandler) {
        return RouterFunctions.route().path("/ping", builder -> builder
                .GET("", pingHandler::handlePing).build()
        ).build();
    }
}