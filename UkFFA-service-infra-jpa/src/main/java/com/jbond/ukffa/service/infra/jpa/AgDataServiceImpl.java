package com.jbond.ukffa.service.infra.jpa;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

public class AgDataServiceImpl implements AgDataService{


    @Override
    public Flux<String> getEnumSchemas(String token) {
        Flux<String> result = WebClient.builder().baseUrl("http://212.77.128.19:17201/ServiceJSON/EnumSchemas")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("session", token).build())
                .retrieve().bodyToFlux(String.class);

        return result;
    }

}
