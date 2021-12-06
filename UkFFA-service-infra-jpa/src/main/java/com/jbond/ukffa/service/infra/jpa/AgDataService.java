package com.jbond.ukffa.service.infra.jpa;

import reactor.core.publisher.Flux;

import java.util.List;

public interface AgDataService {

    Flux<String> getEnumSchemas(String token);
}
