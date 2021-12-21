package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public interface IAgDataService {

    AgFindDevice[] findDevicesByRegNumber(String token, String schemaId, String  regNumber) throws JsonProcessingException;

    long getDurationMoveByTrips(AgTrips agtrips);
    long getDurationMove(AgTrip agtrip);

}
