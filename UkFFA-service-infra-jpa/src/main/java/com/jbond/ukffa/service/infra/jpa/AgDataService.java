package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgEnumDevices;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import com.jbond.ukffa.service.core.entity.agentity.AgTrips;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Dictionary;
import java.util.HashMap;

public interface AgDataService {
    Flux<String> getFluxEnumSchemas(String token);

    Mono<String> getMonoEnumSchemas(String token);

    AgSchema[] getEnumSchemaFromFlux(Flux<String> flux) throws JsonProcessingException;

    AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException;

    Mono<String> getMonoEnumAgDevice(String token, AgSchema schema);

    AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice) throws JsonProcessingException;

    Mono<String> getMonoAgTrips(String token,
                                String schema_id,
                                String[] id_devices,
                                String startDate,
                                String endDate,
                                int tripSplitterIndex);

    HashMap<String, AgTrips> getMapAgTripsFromMono(Mono<String> monoAgTrips) throws JsonProcessingException;
}
