package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.jbond.ukffa.service.core.entity.agentity.AgTrip;
import com.jbond.ukffa.service.core.entity.agentity.AgTripStage;

public interface IAgStagesService {
    AgTripStage[] getTripStages(AgTrip agTrip);
}
