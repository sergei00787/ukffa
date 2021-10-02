package com.jbond.ukffa.service.infra.api.rest;

import com.jbond.ukffa.service.infra.jpa.DeviceJpaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootApplication
public class MockRestApp {

    @MockBean
    public DeviceJpaService deviceJpaService;

    public static void main(String[] args) {
        SpringApplication.run(MockRestApp.class, args);
    }
}
