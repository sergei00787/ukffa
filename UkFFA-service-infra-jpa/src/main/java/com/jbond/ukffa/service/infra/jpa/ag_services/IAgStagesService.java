package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import reactor.core.publisher.Mono;

public interface IAgStagesService {
    Mono<String> getMonoEnumSchemas(String token);
    AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException;
    AgSchema[] getEnumSchema(String login, String password);
}
