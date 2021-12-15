package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.AgEnumDevices;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import com.jbond.ukffa.service.core.entity.agentity.AgTrips;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class AgDataServiceImpl implements AgDataService {

    @Override
    public Flux<String> getFluxEnumSchemas(String token) {
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

    @Override
    public Mono<String> getMonoEnumSchemas(String token) {
        Mono<String> result = WebClient.builder().baseUrl("http://212.77.128.19:17201/ServiceJSON/EnumSchemas")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("session", token).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }

    @Override
    public AgSchema[] getEnumSchemaFromFlux(Flux<String> flux) throws JsonProcessingException {
        List<String> arr = flux.collectList().block();

        for (String s : arr) {
            ObjectMapper objectMapper = new ObjectMapper();
            AgSchema[] schemas = objectMapper.readValue(s, AgSchema[].class);
            return schemas;
        }

        return null;
    }

    @Override
    public AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException {
        String responseStringAgSchemas = mono.block();
        ObjectMapper objectMapper = new ObjectMapper();
        AgSchema[] schemas = objectMapper.readValue(responseStringAgSchemas, AgSchema[].class);
        return schemas;
    }

    @Override
    public Mono<String> getMonoEnumAgDevice(String token, AgSchema schema) {
        Mono<String> result = WebClient.builder().baseUrl("http://212.77.128.19:17201/ServiceJSON/EnumDevices")
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
        return result;
    }

    @Override
    public AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice) throws JsonProcessingException {
        String responseStringAgSchemas = monoEnumAgDevice.block();
        ObjectMapper objectMapper = new ObjectMapper();
        AgEnumDevices agEnumDevices = objectMapper.readValue(responseStringAgSchemas, AgEnumDevices.class);
        return agEnumDevices;
    }


    @Override
    public Mono<String> getMonoAgTrips(String token,
                                       String schema_id,
                                       String[] id_devices,
                                       String startDate,
                                       String endDate,
                                       int tripSplitterIndex) {
        Mono<String> result = WebClient.builder().baseUrl("http://212.77.128.19:17201/ServiceJSON/GetTrips")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(64 * 1024 * 1024))
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("session", token)
                        .queryParam("schemaID", schema_id)
                        .queryParam("IDs", String.join(",", id_devices))
                        .queryParam("SD", startDate)
                        .queryParam("ED", endDate)
                        .queryParam("tripSplitterIndex", tripSplitterIndex)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }

    @Override
    public HashMap<String, AgTrips> getMapAgTripsFromMono(Mono<String> monoAgTrips) throws JsonProcessingException {
        String responseStringAgTrips = monoAgTrips.block();
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, AgTrips> agTrips = objectMapper.readValue(responseStringAgTrips, new TypeReference<HashMap<String, AgTrips>>() {});
        return agTrips;
    }

}
