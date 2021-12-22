package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgTrip;
import com.jbond.ukffa.service.core.entity.agentity.AgTripStage;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public interface IAgStagesService {
    HashMap<String, AgTripStage> getMapAgTripStageFromMono(Mono<String> monoAgStage) throws JsonProcessingException;

    AgTripStage[] getTripStagesInAgTrip(AgTrip agTrip);

    Mono<String> getMonoAgStage(String token,
                                       String schema_id,
                                       String[] id_devices,
                                       String startDate,
                                       String endDate,
                                       String stageName,
                                       String[] tripParams,
                                       int tripSplitterIndex);

    AgTripStage getAgTripStageFromMap(HashMap<String, AgTripStage> map, String deviceId);

    AgTripStage getTripStage(String login,
                             String password,
                             String schema_id,
                             String id_device,
                             String startDate,
                             String endDate,
                             String stageName,
                             String[] tripParams,
                             int tripSplitterIndex) throws JsonProcessingException;
}
