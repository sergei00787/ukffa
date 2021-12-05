package com.jbond.ukffa.service.infra.jpa;

import reactor.core.publisher.Mono;

public interface AgLoginService {

    Mono<String> getToken(String login, String password);

}
