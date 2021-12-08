package com.jbond.ukffa.service.infra.jpa;

import com.jbond.ukffa.service.core.entity.AgSchema;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface AgDataService {
    Flux<String> getEnumSchemas(String token);
}
