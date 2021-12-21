package com.jbond.ukffa.service.infra.jpa.ag_services;

import com.jbond.ukffa.service.core.entity.agentity.AgEnumDevices;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;

public interface IAgEnumDevicesService {
    AgEnumDevices getAgEnumDevices(String token, AgSchema schema);
}
