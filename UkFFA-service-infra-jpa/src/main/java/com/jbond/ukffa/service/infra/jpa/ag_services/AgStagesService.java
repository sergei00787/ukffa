package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.AgTrip;
import com.jbond.ukffa.service.core.entity.agentity.AgTripStage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public class AgStagesService implements IAgStagesService {
    public String baseAgUrl;

    public AgStagesService(String baseUrl) {
        this.baseAgUrl = baseUrl;
    }

    @Override
    public AgTripStage[] getTripStagesInAgTrip(AgTrip agTrip) {
        return agTrip.getStages();
    }

    @Override
    public Mono<String> getMonoAgStage(String token,
                                       String schema_id,
                                       String[] id_devices,
                                       String startDate,
                                       String endDate,
                                       String stageName,
                                       String[] tripParams,
                                       int tripSplitterIndex) {
        String strTripParams;
        if (tripParams == null) {
            strTripParams = "*";
        } else {
            strTripParams = String.join(",", tripParams);
        }

        String finalStrTripParams = strTripParams;

        return WebClient.builder().baseUrl(this.baseAgUrl + "/GetStage")
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
                        .queryParam("stageName", stageName)
                        .queryParam("tripParams", finalStrTripParams)
                        .queryParam("tripSplitterIndex", tripSplitterIndex)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

    }

    @Override
    public HashMap<String, AgTripStage> getMapAgTripStageFromMono(Mono<String> monoAgStage) throws JsonProcessingException {
        String responseStringAgTrips = monoAgStage.block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseStringAgTrips, new TypeReference<>() {
        });
    }

    @Override
    public AgTripStage getAgTripStageFromMap(HashMap<String, AgTripStage> map, String deviceId) {
        return map.get(deviceId);
    }

    @Override
    public AgTripStage getTripStage(String login,
                                    String password,
                                    String schema_id,
                                    String id_device,
                                    String startDate,
                                    String endDate,
                                    String stageName,
                                    String[] tripParams,
                                    int tripSplitterIndex) throws JsonProcessingException {
        AgLoginService agLoginService = new AgLoginService(baseAgUrl);
        String token = agLoginService.getToken(login, password);

        Mono<String> monoAgTrips = getMonoAgStage(token,
                schema_id,
                new String[]{id_device},
                startDate,
                endDate,
                stageName,
                tripParams,
                tripSplitterIndex);
        HashMap<String, AgTripStage> mapAgTripStage = getMapAgTripStageFromMono(monoAgTrips);
        return getAgTripStageFromMap(mapAgTripStage, id_device);
    }


}
