package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbond.ukffa.service.core.entity.agentity.*;
import com.jbond.ukffa.service.core.utility.AgDateUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgDataServiceImpl implements AgDataService {
    public String baseAgUrl;

    public AgDataServiceImpl(String baseUrl){
        this.baseAgUrl = baseUrl;
    }

    @Override
    public Mono<String> getMonoEnumAgDevice(String token, AgSchema schema) {
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

    @Override
    public AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice) throws JsonProcessingException {
        String responseStringAgSchemas = monoEnumAgDevice.block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseStringAgSchemas, AgEnumDevices.class);
    }

    @Override
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

    @Override
    public HashMap<String, AgTrips> getMapAgTripsFromMono(Mono<String> monoAgTrips) throws JsonProcessingException {
        String responseStringAgTrips = monoAgTrips.block();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseStringAgTrips, new TypeReference<HashMap<String, AgTrips>>() {
        });
    }

    @Override
    public AgFindDevice[] findDevicesByRegNumber(String token, String schemaId, String regNumber) throws JsonProcessingException {
        String agDeviceItem = WebClient.builder().baseUrl(this.baseAgUrl + "/FindDevices")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(64 * 1024 * 1024))
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("session", token)
                        .queryParam("schemaID", schemaId)
                        .queryParam("vehicleRegNumbers", regNumber)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(agDeviceItem, AgFindDevice[].class);
    }

    @Override
    public List<AgTrips> getAgTrips(HashMap<String, AgTrips> map, String deviceId) {
        ArrayList<AgTrips> listAgTrips = new ArrayList<>();
        AgTrips agTrips = map.get(deviceId);
        listAgTrips.add(agTrips);
        return listAgTrips;
    }

    @Override
    public long getDurationMoveByTrips(AgTrips agtrips) {
        long sumDurationMove = 0L;
        for (AgTrip agTrip : agtrips.getTrips()) {
            sumDurationMove = sumDurationMove + getDurationMove(agTrip);
        }
        return sumDurationMove;
    }

    @Override
    public long getDurationMove(AgTrip agtrip) {
        return AgDateUtility.getSecondFromStringDuration((String) agtrip.getTotal().get("MoveDuration"));
    }

}
