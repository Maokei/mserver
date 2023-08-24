package se.maokei.mserver.api.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import se.maokei.mserver.api.v1.handler.PingHandler;

public class PingRouter {
    @Bean
    public RouterFunction<ServerResponse> route(PingHandler pingHandler) {
        return RouterFunctions.route().path("/ping", builder -> builder.GET(pingHandler::handlePing)).build();
    }
}
