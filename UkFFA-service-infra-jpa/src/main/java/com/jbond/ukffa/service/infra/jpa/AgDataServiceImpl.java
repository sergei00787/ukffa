package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.AgSchema;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

public class AgDataServiceImpl implements AgDataService {

    @Override
    public Flux<String> getEnumSchemas(String token) {
        Flux<String> result = WebClient.builder().baseUrl("http://212.77.128.19:17201/ServiceJSON/EnumSchemas")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("session", token).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(String.class);

        return result;
    }

}
