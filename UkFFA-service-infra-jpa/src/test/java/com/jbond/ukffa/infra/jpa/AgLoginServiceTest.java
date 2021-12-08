package com.jbond.ukffa.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.AgSchema;
import com.jbond.ukffa.service.infra.jpa.AgDataServiceImpl;
import com.jbond.ukffa.service.infra.jpa.AgLoginServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AgLoginServiceTest {

    @Test
    public void testGetAgLoginToken() {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> result = agLoginService.getToken("test_read_only", "tst123");
        System.out.println(result.block());
    }

    @Test
    public void testGetAgEnumSchemas() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "tst123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Flux<String> data = agDataService.getEnumSchemas(token.block());

        List<String> arr = data.collectList().block();


        for (String s : arr) {
            ObjectMapper objectMapper = new ObjectMapper();
            AgSchema[] schemas = objectMapper.readValue(s, AgSchema[].class);
            System.out.println("----ARR-------------------------------------------------------");

            for (AgSchema ags: schemas) {
                //AgSchema agSchema = objectMapper.readValue(ags, AgSchema.class);
                System.out.println(ags);
            }

        }

    }

}
