package se.maokei.mserver.api.v1.handler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@Component
@OpenAPIDefinition(
        info = @Info(
                title = "Ping handler",
                description = "Ping for signs of life."
        )
)
public class PingHandler {
    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ping", content = {
                    @Content(mediaType = "text/plain")
            })
    })
    public Mono<ServerResponse> handlePing(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("alive"), String.class);
    }
}
