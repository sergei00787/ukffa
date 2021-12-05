package com.jbond.ukffa.infra.jpa;

import com.jbond.ukffa.service.infra.jpa.AgLoginServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class AgLoginServiceTest {

    @Test
    public void testGetAgLoginToken(){
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> result = agLoginService.getToken("test_read_only", "tst123");
        System.out.println(result.block());
    }

}
