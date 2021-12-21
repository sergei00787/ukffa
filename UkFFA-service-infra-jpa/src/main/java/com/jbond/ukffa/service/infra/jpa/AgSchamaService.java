package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import reactor.core.publisher.Mono;

public interface AgSchamaService {
    Mono<String> getMonoEnumSchemas(String token);
    AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException;
    AgSchema[] getEnumSchema(String login, String password);
}
