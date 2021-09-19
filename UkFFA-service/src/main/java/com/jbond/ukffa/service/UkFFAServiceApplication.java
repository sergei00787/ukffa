package com.jbond.ukffa.service;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class UkFFAServiceApplication {

    public static void main(String[] args) {
        Environment environment = SpringApplication.run(UkFFAServiceApplication.class, args).getEnvironment();
        log.info("Start SwaggerUI to use the Shopping List's APIs: "
                + "http://localhost:{}/swagger-ui/index.html#/shopping-list-controller", environment.getProperty("server.port"));
    }

}
