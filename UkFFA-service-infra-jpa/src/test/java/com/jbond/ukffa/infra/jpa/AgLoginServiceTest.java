package com.jbond.ukffa.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.AgSchema;
import com.jbond.ukffa.service.infra.jpa.AgDataServiceImpl;
import com.jbond.ukffa.service.infra.jpa.AgLoginServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgLoginServiceTest {

    @Test
    public void testGetAgLoginToken() {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> result = agLoginService.getToken("test_read_only", "test123");
        System.out.println(result.block());
    }

    @Test
    public void testGetAgEnumSchemas() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Flux<String> data = agDataService.getFluxEnumSchemas(token.block());

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromFlux(data);

        for (AgSchema as: agSchemas) {
            System.out.println(as.toString());
        }
    }

    @Test
    public void testGetAgEnumSchemas2() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token.block());

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        for (AgSchema as: agSchemas) {
            System.out.println(as.toString());
        }
    }

}
