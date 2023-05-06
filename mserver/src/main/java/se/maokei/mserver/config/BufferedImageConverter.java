package se.maokei.mserver.config;

import java.awt.image.BufferedImage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.AbstractSingleValueEncoder;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import reactor.core.publisher.Mono;

@Configuration
public class BufferedImageConverter /*extends AbstractSingleValueEncoder<Object>*/ {

    /*@Bean
    public Mono<HttpMessageConverter<BufferedImage>> httpMessageConverter() {
        return Mono.just(new BufferedImageHttpMessageConverter());
    }*/


}