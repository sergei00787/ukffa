package com.jbond.ukffa.service.infra.jpa.ag_services;

import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AgLoginService implements IAgLoginService {

    private final String BASE_URL = "http://212.77.128.19:17201/ServiceJSON/Login";

    private Mono<String> getMonoToken(String login, String password) {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("UserName", login);
        map.add("Password", password);

        Mono<String> monoToken = WebClient.builder()
                .baseUrl(BASE_URL)
                .build()
                .post()
                .body(BodyInserters.fromMultipartData(map))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Client Error"+ clientResponse.logPrefix())))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Server Error"+ clientResponse.logPrefix())))
                .bodyToMono(String.class);
        return monoToken;
    }

    @Override
    public String getToken(String login, String password){
        return getMonoToken(login, password).block();
    }


}
