package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.AgDeviceItem;
import com.jbond.ukffa.service.core.entity.AgEnumDevices;
import com.jbond.ukffa.service.core.entity.AgSchema;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AgDataService {
    Flux<String> getFluxEnumSchemas(String token);

    Mono<String> getMonoEnumSchemas(String token);

    AgSchema[] getEnumSchemaFromFlux(Flux<String> flux) throws JsonProcessingException;

    AgSchema[] getEnumSchemaFromMono(Mono<String> mono) throws JsonProcessingException;

    Mono<String> getMonoEnumAgDevice(String token, AgSchema schema);

    AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice) throws JsonProcessingException;

}
