package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AgSchemaService implements IAgSchamaService {
    public String baseAgUrl;

    public AgSchemaService(String baseUrl){
        this.baseAgUrl = baseUrl;
    }

    @Override
    public Mono<String> getMonoEnumSchemas(String token) {
        return WebClient.builder().baseUrl(this.baseAgUrl + "/EnumSchemas")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("session", token).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException {
        String responseStringAgSchemas = mono.block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseStringAgSchemas, AgSchema[].class);
    }

    @Override
    public AgSchema[] getEnumSchema(String login, String password) {
        AgLoginService agLoginService = new AgLoginService(baseAgUrl);
        String token = agLoginService.getToken(login, password);
        try {
            return getEnumSchemaFromMono(getMonoEnumSchemas(token));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new AgSchema[]{};
    }
}
