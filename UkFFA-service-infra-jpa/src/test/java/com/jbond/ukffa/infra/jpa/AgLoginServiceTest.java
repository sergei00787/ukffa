package com.jbond.ukffa.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.*;
import com.jbond.ukffa.service.infra.jpa.ag_services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AgLoginServiceTest {

    public String baseURL = "http://212.77.128.19:17201/ServiceJSON";

    @Test
    public void testGetAgLoginToken() {
        AgLoginService agLoginService = new AgLoginService(baseURL);
        String result = agLoginService.getToken("test_read_only", "test123");

        String expectedToken = "F8C5D8E87B38BAED2D93F798A992E7359D8DD5684E6D9A68E1AE362192FC1FA2";

        assertEquals(expectedToken, result);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> agLoginService.getToken("tess", "test3"));
    }

    @Test
    public void testGetAgEnumSchemas() throws JsonProcessingException {
        AgLoginService agLoginService = new AgLoginService(baseURL);
        String token = agLoginService.getToken("test_read_only", "test123");

        AgSchemaService agSchemaService = new AgSchemaService(baseURL);
        Mono<String> data = agSchemaService.getMonoEnumSchemas(token);

        AgSchema[] agSchemas = agSchemaService.getEnumSchemaFromMono(data);

        String[] expectedAgSchemaNames = new String[]{"ООО ПромАльянс", "ПАО Мечел", "ПАО ЮК"};
        String[] actualAgSchemaNames = new String[3];
        int i = 0;
        for (AgSchema as : agSchemas) {
            actualAgSchemaNames[i] = as.getName();
            i++;
        }
        Assertions.assertArrayEquals(expectedAgSchemaNames, actualAgSchemaNames);
    }

    @Test
    public void testGetAgEnumSchemas2(){
        AgSchemaService agSchemaService = new AgSchemaService(baseURL);
        AgSchema[] agSchemas =  agSchemaService.getEnumSchema("test_read_only", "test123");

        String[] expectedAgSchemaNames = new String[]{"ООО ПромАльянс", "ПАО Мечел", "ПАО ЮК"};
        String[] actualAgSchemaNames = new String[3];
        int i = 0;
        for (AgSchema as : agSchemas) {
            actualAgSchemaNames[i] = as.getName();
            i++;
        }
        Assertions.assertArrayEquals(expectedAgSchemaNames, actualAgSchemaNames);
    }

    @Test
    public void testGetAgEnumDevices() throws JsonProcessingException {
        IAgLoginService agLoginService = new AgLoginService(baseURL);
        String token = agLoginService.getToken("test_read_only", "test123");


        IAgSchamaService agSchemaService = new AgSchemaService(baseURL);
        IAgEnumDevicesService agEnumDevicesService = new AgEnumDevicesService(baseURL);

        Mono<String> data = agSchemaService.getMonoEnumSchemas(token);
        AgSchema[] agSchemas = agSchemaService.getEnumSchemaFromMono(data);

        for (AgSchema schema : agSchemas) {
            AgEnumDevices agEnumDevices = agEnumDevicesService.getAgEnumDevices(token, schema);
            assertNotNull(agEnumDevices);
        }
    }


    @Test
    @DisplayName("Test get ag trips and return duration move")
    public void testGetAgTrips() throws JsonProcessingException {
        IAgDataService agDataService = new AgDataService(baseURL);
        IAgTripsService agTripsService = new AgTripsService(baseURL);

        List<AgTrips> agTrips = agTripsService.getTrips("test_read_only",
                "test123",
                "d28e3930-7faa-469d-9551-7ed561830b09",
                "8f42b56a-f8ca-4214-b2d2-1d7a0b532dab",
                "20211215-0800",
                "20211215-1200",
                -1);

        for (AgTrips trips: agTrips) {
            long sumDurationMove = agDataService.getDurationMoveByTrips(trips);
            assertEquals(sumDurationMove, 7868);
        }

    }

    @Test
    public void testFindDevices() throws JsonProcessingException {
        AgLoginService agLoginService = new AgLoginService(baseURL);
        String token = agLoginService.getToken("test_read_only", "test123");

        IAgDataService agDataService = new AgDataService(baseURL);

        AgFindDevice[] agFindDevices = agDataService.findDevicesByRegNumber(token,"d28e3930-7faa-469d-9551-7ed561830b09","р923ет");

        String expectedId = "ff903609-7f0e-4f48-89d6-f860e024e62d";
        for (AgFindDevice agFindDevice : agFindDevices) {
            assertEquals(expectedId, agFindDevice.getPath()[0].getId());
            assertEquals(agFindDevice.getItem().getSerial(), 1390599);
        }
        assertEquals(1, agFindDevices.length);
    }

    @Test
    @DisplayName("Test get ag stages")
    public void testGetAgTripStages() throws JsonProcessingException {
        IAgDataService agDataService = new AgDataService(baseURL);
        IAgTripsService agTripsService = new AgTripsService(baseURL);
        IAgStagesService agStagesService = new AgStagesService(baseURL);

        List<AgTrips> agTrips = agTripsService.getTrips("test_read_only",
                "test123",
                "d28e3930-7faa-469d-9551-7ed561830b09",
                "8f42b56a-f8ca-4214-b2d2-1d7a0b532dab",
                "20211215-0800",
                "20211215-1200",
                -1);

        for (AgTrips trips: agTrips) {
            for(AgTrip agTrip: trips.getTrips()){
                AgTripStage[] agTripStages = agStagesService.getTripStagesInAgTrip(agTrip);
                for (AgTripStage agTripStage: agTripStages){
                    System.out.println(agTripStage.getItems());
                }
            }
        }

    }

}
