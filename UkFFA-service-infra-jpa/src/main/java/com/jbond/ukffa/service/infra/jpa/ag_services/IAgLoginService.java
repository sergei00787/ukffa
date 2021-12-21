package com.jbond.ukffa.service.infra.jpa.ag_services;

import reactor.core.publisher.Mono;

public interface IAgLoginService {

    String getToken(String login, String password);

}
