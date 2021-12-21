package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public interface AgDataService {

    Mono<String> getMonoEnumAgDevice(String token, AgSchema schema);

    AgEnumDevices getAgEnumDevicesFromMono(Mono<String> monoEnumAgDevice) throws JsonProcessingException;

    Mono<String> getMonoAgTrips(String token,
                                String schema_id,
                                String[] id_devices,
                                String startDate,
                                String endDate,
                                int tripSplitterIndex);

    HashMap<String, AgTrips> getMapAgTripsFromMono(Mono<String> monoAgTrips) throws JsonProcessingException;
    List<AgTrips> getAgTrips(HashMap<String, AgTrips> map, String deviceId);

    AgFindDevice[] findDevicesByRegNumber(String token, String schemaId, String  regNumber) throws JsonProcessingException;

    long getDurationMoveByTrips(AgTrips agtrips);
    long getDurationMove(AgTrip agtrip);

}
