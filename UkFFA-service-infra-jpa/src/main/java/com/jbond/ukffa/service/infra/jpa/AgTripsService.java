package com.jbond.ukffa.service.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgTrips;

import java.util.List;

public interface AgTripsService {
    List<AgTrips> getTrips(String login, String password,
                           String schema_id,
                           String id_device,
                           String startDate,
                           String endDate,
                           int tripSplitterIndex) throws JsonProcessingException;
}
