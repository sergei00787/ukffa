package com.jbond.ukffa.service.infra.jpa.ag_services;

public interface IAgLoginService {

    String getToken(String login, String password);

}
