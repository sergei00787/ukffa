package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.jbond.ukffa.service.core.entity.agentity.AgTrip;
import com.jbond.ukffa.service.core.entity.agentity.AgTripStage;

public class AgStagesService implements IAgStagesService {
    public String baseAgUrl;

    public AgStagesService(String baseUrl) {
        this.baseAgUrl = baseUrl;
    }

    @Override
    public AgTripStage[] getTripStages(AgTrip agTrip) {
        return agTrip.getStages();
    }
}
