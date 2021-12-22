package com.jbond.ukffa.infra.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbond.ukffa.service.core.entity.agentity.AgTripStage;
import com.jbond.ukffa.service.infra.jpa.ag_services.AgLoginService;
import com.jbond.ukffa.service.infra.jpa.ag_services.AgStagesService;
import com.jbond.ukffa.service.infra.jpa.ag_services.IAgLoginService;
import com.jbond.ukffa.service.infra.jpa.ag_services.IAgStagesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.HashMap;

public class AgStagesServiceTest {

    public String baseURL = "http://212.77.128.19:17201/ServiceJSON";

    @Test
    @DisplayName("Test get mono AG stage")
    public void testGetMonoAgStages() {
        String expectedString = "{\"_SD\":\"2021-12-15T11:00:24Z\",\"_ED\":\"2021-12-15T11:00:31Z\",\"Index\":124,\"SD\":\"2021-12-15T11:00:24\",\"ED\":\"2021-12-15T11:00:31\",\"Status\":1,\"StatusID\":\"00000000-0000-0000-0000-000000000000\",\"StatusIDs\":null,\"StartPoint\":{\"Lat\":53.77670609,\"Lng\":88.09086963},\"EndPoint\":{\"Lat\":53.77684427,\"Lng\":88.09103343},\"Caption\":\"Остановка\",\"Values\":[\"2021-12-15T11:00:24\",\"2021-12-15T11:00:31\",88.0908696,88.0910334,53.7767061,53.7768443,\"2021-12-15T11:00:24\",\"2021-12-15T11:00:31\",\"00:00:07\",\"00:00:00\",\"00:00:07\",0.0197658,\"2021-12-15T11:00:24\",\"2021-12-15T11:00:31\",null,null,1,12.2664805,";

        IAgLoginService agLoginService = new AgLoginService(baseURL);
        String token = agLoginService.getToken("test_read_only", "test123");

        IAgStagesService agStagesService = new AgStagesService(baseURL);

        Mono<String> mono = agStagesService.getMonoAgStage(token,
                "d28e3930-7faa-469d-9551-7ed561830b09",
                new String[]{"8f42b56a-f8ca-4214-b2d2-1d7a0b532dab"},
                "20211215-0800",
                "20211215-1200",
                "Motion",
                null,
                -1);

        Assertions.assertTrue(mono.block().contains(expectedString));
    }

    @Test
    @DisplayName("Test get hashmap AG stage")
    public void testGetHashMapAgStages() throws JsonProcessingException {


        IAgLoginService agLoginService = new AgLoginService(baseURL);
        String token = agLoginService.getToken("test_read_only", "test123");

        IAgStagesService agStagesService = new AgStagesService(baseURL);

        Mono<String> mono = agStagesService.getMonoAgStage(token,
                "d28e3930-7faa-469d-9551-7ed561830b09",
                new String[]{"8f42b56a-f8ca-4214-b2d2-1d7a0b532dab"},
                "20211215-0800",
                "20211215-1200",
                "Motion",
                null,
                -1);

        HashMap<String, AgTripStage> map = agStagesService.getMapAgTripStageFromMono(mono);

        Assertions.assertTrue(map.containsKey("8f42b56a-f8ca-4214-b2d2-1d7a0b532dab"));
        Assertions.assertNotNull(map.get("8f42b56a-f8ca-4214-b2d2-1d7a0b532dab"));
    }

    @Test
    @DisplayName("Test get AG stage")
    public void testGetAgStages() throws JsonProcessingException {
        IAgStagesService agStagesService = new AgStagesService(baseURL);

        AgTripStage agTripStage = agStagesService.getTripStage("test_read_only",
                "test123",
                "d28e3930-7faa-469d-9551-7ed561830b09",
                "8f42b56a-f8ca-4214-b2d2-1d7a0b532dab",
                "20211215-0800",
                "20211215-1200",
                "Motion",
                null,
                -1);

        Assertions.assertNotNull(agTripStage);
    }


}
