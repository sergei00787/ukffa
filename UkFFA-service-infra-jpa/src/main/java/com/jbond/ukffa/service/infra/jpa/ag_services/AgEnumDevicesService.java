package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.AgEnumDevices;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AgEnumDevicesService implements IAgEnumDevicesService {
    public String baseAgUrl;

    public AgEnumDevicesService(String baseUrl){
        this.baseAgUrl = baseUrl;
    }


    @Override
    public AgEnumDevices getAgEnumDevices(String token, AgSchema schema) {
        return getAgEnumDevicesFromMono(getMonoEnumAgDevice(token,schema));
    }

    private Mono<String> getMonoEnumAgDevice(String token, AgSchema schema) {
        return WebClient.builder().baseUrl(this.baseAgUrl + "/EnumDevices")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("session", token)
                        .queryParam("schemaID", schema.getId())
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

    private AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice)  {
        String responseStringAgSchemas = monoEnumAgDevice.block();
        ObjectMapper objectMapper = new ObjectMapper();
        AgEnumDevices agEnumDevices = null;
        try {
            agEnumDevices =  objectMapper.readValue(responseStringAgSchemas, AgEnumDevices.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return agEnumDevices;
    }
}
