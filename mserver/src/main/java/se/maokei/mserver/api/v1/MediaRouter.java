package se.maokei.mserver.api.v1;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import se.maokei.mserver.api.v1.handler.MediaHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Tag(name = "Media API", description = "Api to access and manage audio and video media")
@Configuration(proxyBeanMethods = false)
public class MediaRouter {
  @Bean
  public RouterFunction<ServerResponse> route(MediaHandler mediaHandler) {
    return RouterFunctions
        .route().nest(
            path("/api/v1/media"), builder -> builder
                .GET("", mediaHandler::listAllMedia)
                .nest(path("/{foreignId}"), mediaBuilder -> mediaBuilder
                    .GET("", mediaHandler::getMedia)
                ).build()
        ).build();
  }
}