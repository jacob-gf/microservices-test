package com.jacob.microservice.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat("dd/MM/yyyy");
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        };
    }
}
