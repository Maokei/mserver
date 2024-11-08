package se.maokei.mserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class R2dbcConfig {
    private final ObjectMapper objectMapper;

    public R2dbcConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public R2dbcCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new HashMapReadConverter(objectMapper));
        converters.add(new HashMapWriteConverter(objectMapper));
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters);
    }

    @Slf4j
    @ReadingConverter
    @AllArgsConstructor
    static class HashMapReadConverter implements Converter<Json, HashMap<String, String>> {
        private final ObjectMapper objectMapper;

        @Override
        public HashMap<String, String> convert(Json json) {
            try {
                return objectMapper.readValue(json.asArray(), new TypeReference<HashMap<String, String>>(){});
            } catch (IOException e) {
                log.error("Error parsing metadata Json from DB", e);
                throw new RuntimeException(e);
            }
        }
    }

    @Slf4j
    @WritingConverter
    @AllArgsConstructor
    static class HashMapWriteConverter implements Converter<HashMap<String, String>, Json> {
        private final ObjectMapper objectMapper;

        @Override
        public Json convert(HashMap<String, String> source) {
            try {
                return Json.of(objectMapper.writeValueAsString(source));
            } catch (JsonProcessingException e) {

                throw new RuntimeException(e);
            }
        }
    }

    public static <T> String toJson(T obj) {
        String json = null;
        if (obj != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                json = objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                //log.warn(e.getMessage(), e);
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return json;
    }
}