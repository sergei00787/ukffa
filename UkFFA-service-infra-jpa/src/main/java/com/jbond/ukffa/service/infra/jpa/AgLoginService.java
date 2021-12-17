package com.jbond.ukffa.service.infra.jpa;

import reactor.core.publisher.Mono;

public interface AgLoginService {

    Mono<String> getMonoToken(String login, String password);
    String getToken(String login, String password);

}
