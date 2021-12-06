package com.jbond.ukffa.infra.jpa;

import com.jbond.ukffa.service.infra.jpa.AgDataServiceImpl;
import com.jbond.ukffa.service.infra.jpa.AgLoginServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class AgLoginServiceTest {

    @Test
    public void testGetAgLoginToken(){
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> result = agLoginService.getToken("test_read_only", "tst123");
        System.out.println(result.block());
    }

    @Test
    public void testGetAgEnumSchemas(){
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "tst123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Flux<String> data = agDataService.getEnumSchemas(token.block());

        List<String> arr = data.collectList().block();

        for (String s: arr) {
            System.out.println(s);
        }

    }

}
