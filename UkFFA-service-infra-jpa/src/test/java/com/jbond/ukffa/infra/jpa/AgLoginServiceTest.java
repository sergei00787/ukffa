package com.jbond.ukffa.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.*;
import com.jbond.ukffa.service.core.utility.AgDateUtility;
import com.jbond.ukffa.service.infra.jpa.AgDataServiceImpl;
import com.jbond.ukffa.service.infra.jpa.AgLoginServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class AgLoginServiceTest {

    @Test
    public void testGetAgLoginToken() {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        String result = agLoginService.getToken("test_read_only", "test123");
        System.out.println(result);
    }

    @Test
    public void testGetAgEnumSchemas() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        String token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token);

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        for (AgSchema as : agSchemas) {
            System.out.println(as.toString());
        }
    }

    @Test
    public void testGetAgEnumSchemas2() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        String token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token);

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        for (AgSchema as : agSchemas) {
            System.out.println(as.toString());
        }
    }

    @Test
    public void testGetAgEnumDevices() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        String token = agLoginService.getToken("test_read_only", "test123");


        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token);

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        for (AgSchema schema : agSchemas) {
            Mono<String> monoAgEnumDevices = agDataService.getMonoEnumAgDevice(token, schema);

            AgEnumDevices agEnumDevices = agDataService.getAgEnumDevicesFromMono(monoAgEnumDevices);

            for (AgDeviceItem items : agEnumDevices.getItems()) {
                System.out.println(items.getProperties());
            }
        }
    }

    @Test
    public void testGetAgTrips() throws JsonProcessingException, ParseException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        String token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();

        String[] ids = new String[1];
        ids[0] = "8f42b56a-f8ca-4214-b2d2-1d7a0b532dab";

        Mono<String> agTripsMono = agDataService.getMonoAgTrips(token,
                "d28e3930-7faa-469d-9551-7ed561830b09",
                ids,
                AgDateUtility.convertAgStrLocalTimeToAgStrGMTTime("20211215-0800"),
                AgDateUtility.convertAgStrLocalTimeToAgStrGMTTime("20211215-1200"),
                -1
        );

        HashMap<String, AgTrips> mapAgTrips = agDataService.getMapAgTripsFromMono(agTripsMono);

        for (Map.Entry<String, AgTrips> entry : mapAgTrips.entrySet()) {
            AgTrips trips = entry.getValue();
            AgTrip[] agTrips = trips.getTrips();

            long sumDurationMove = agDataService.getSumDurationMoveByTrips(trips);
        }

    }

    @Test
    public void testFindDevices() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        String token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();

        AgFindDevice[] agFindDevices = agDataService.findDevicesByRegNumber(token,
                "d28e3930-7faa-469d-9551-7ed561830b09",
                "р474ку");

        System.out.println(agFindDevices.length);
        for (AgFindDevice agFindDevice: agFindDevices) {
            System.out.println(agFindDevice);
        }

    }


}
