package com.jbond.ukffa.service.infra.jpa;

import reactor.core.publisher.Flux;

public interface AgDataService {

    Flux<String> getEnumSchemas(String token);
}
