package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.AgSchema;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AgDataService {
    Flux<String> getFluxEnumSchemas(String token);

    Mono<String> getMonoEnumSchemas(String token);

    AgSchema[] getEnumSchemaFromFlux(Flux<String> flux) throws JsonProcessingException;

    AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException;



}
