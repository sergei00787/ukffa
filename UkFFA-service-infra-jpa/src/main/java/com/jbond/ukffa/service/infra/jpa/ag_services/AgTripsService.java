package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.AgTrips;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgTripsService implements IAgTripsService {
    public String baseAgUrl;

    public AgTripsService(String baseUrl){
        this.baseAgUrl = baseUrl;
    }

    public Mono<String> getMonoAgTrips(String token,
                                       String schema_id,
                                       String[] id_devices,
                                       String startDate,
                                       String endDate,
                                       int tripSplitterIndex) {
        return WebClient.builder().baseUrl(this.baseAgUrl + "/GetTrips")
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
    }

    public HashMap<String, AgTrips> getMapAgTripsFromMono(Mono<String> monoAgTrips) throws JsonProcessingException {
        String responseStringAgTrips = monoAgTrips.block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseStringAgTrips, new TypeReference<>() {
        });
    }

    public List<AgTrips> getListAgTrips(HashMap<String, AgTrips> map, String deviceId) {
        ArrayList<AgTrips> listAgTrips = new ArrayList<>();
        AgTrips agTrips = map.get(deviceId);
        listAgTrips.add(agTrips);
        return listAgTrips;
    }

    @Override
    public List<AgTrips> getTrips(String login, String password,
                              String schema_id,
                              String id_device,
                              String startDate,
                              String endDate,
                              int tripSplitterIndex) throws JsonProcessingException {
        AgLoginService agLoginService = new AgLoginService(baseAgUrl);
        String token = agLoginService.getToken(login, password);
        Mono<String> monoAgTrips = getMonoAgTrips(token,schema_id,new String[]{id_device},startDate,endDate, tripSplitterIndex);
        HashMap<String, AgTrips> mapAgTrips = getMapAgTripsFromMono(monoAgTrips);
        return getListAgTrips(mapAgTrips, id_device);
    }


}
