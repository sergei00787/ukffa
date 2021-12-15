package com.jbond.ukffa.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgDeviceItem;
import com.jbond.ukffa.service.core.entity.agentity.AgEnumDevices;
import com.jbond.ukffa.service.core.entity.agentity.AgSchema;
import com.jbond.ukffa.service.core.entity.agentity.AgTrips;
import com.jbond.ukffa.service.infra.jpa.AgDataServiceImpl;
import com.jbond.ukffa.service.infra.jpa.AgLoginServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AgLoginServiceTest {

    @Test
    public void testGetAgLoginToken() {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> result = agLoginService.getToken("test_read_only", "test123");
        System.out.println(result.block());
    }

    @Test
    public void testGetAgEnumSchemas() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Flux<String> data = agDataService.getFluxEnumSchemas(token.block());

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromFlux(data);

        for (AgSchema as: agSchemas) {
            System.out.println(as.toString());
        }
    }

    @Test
    public void testGetAgEnumSchemas2() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "test123");

        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token.block());

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        for (AgSchema as: agSchemas) {
            System.out.println(as.toString());
        }
    }

    @Test
    public void testGetAgEnumDevices() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
        Mono<String> token = agLoginService.getToken("test_read_only", "test123");


        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token.block());

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        for (AgSchema schema: agSchemas) {
            Mono<String> monoAgEnumDevices = agDataService.getMonoEnumAgDevice(token.block(), schema);
            String strMono = monoAgEnumDevices.block();

            AgEnumDevices agEnumDevices = agDataService.getAgEnumDevicesFromMono(monoAgEnumDevices);

            for (AgDeviceItem items: agEnumDevices.getItems()) {
                System.out.println(items.getProperties());
            }
        }
    }

    @Test
    public void testGetAgTrips() throws JsonProcessingException {
        AgLoginServiceImpl agLoginService = new AgLoginServiceImpl();
//        Mono<String> token = agLoginService.getToken("test_read_only", "test123");
        Mono<String> token = agLoginService.getToken("admin", "POIUYTREWQ!@89");


        AgDataServiceImpl agDataService = new AgDataServiceImpl();
        Mono<String> data = agDataService.getMonoEnumSchemas(token.block());

        AgSchema[] agSchemas = agDataService.getEnumSchemaFromMono(data);

        //AgSchema(id=d28e3930-7faa-469d-9551-7ed561830b09, name=ПАО ЮК)
        //b48cb5c7-0094-4f59-8398-61f41eddda8c

        String[] ids = new String[1];
        ids[0] = "8f42b56a-f8ca-4214-b2d2-1d7a0b532dab";

        Mono<String> agTripsMono = agDataService.getMonoAgTrips(token.block(),
                "d28e3930-7faa-469d-9551-7ed561830b09",
                ids,
                "20211215-2115",
                "20211216-2117",
                -1
                );
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(agTripsMono.block());
        
        HashMap<String, AgTrips> mapAgTrips = agDataService.getMapAgTripsFromMono(agTripsMono);

        System.out.println(mapAgTrips);

        /*
        for (AgSchema schema: agSchemas) {
            Mono<String> monoAgEnumDevices = agDataService.getMonoEnumAgDevice(token.block(), schema);
            String strMono = monoAgEnumDevices.block();

            AgEnumDevices agEnumDevices = agDataService.getAgEnumDevicesFromMono(monoAgEnumDevices);

            for (AgDeviceItem items: agEnumDevices.getItems()) {
                System.out.println(items.getId());
            }
        }
        */

    }



}
