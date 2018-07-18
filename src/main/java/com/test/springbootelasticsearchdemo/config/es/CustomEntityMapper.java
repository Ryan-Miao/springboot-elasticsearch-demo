package com.test.springbootelasticsearchdemo.config.es;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.geo.CustomGeoModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Ryan Miao at 2018-07-18 16:03
 **/
@Component
public class CustomEntityMapper extends DefaultEntityMapper {

    private ObjectMapper objectMapper;


    @Autowired
    public CustomEntityMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        this.objectMapper = jackson2ObjectMapperBuilder.createXmlMapper(false).build();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.objectMapper.registerModule(new CustomGeoModule());
        this.objectMapper.registerModule(new JavaTimeModule());
//        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public String mapToString(Object object) throws IOException {
        return this.objectMapper.writeValueAsString(object);
    }

    @Override
    public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
        return this.objectMapper.readValue(source, clazz);
    }


}
