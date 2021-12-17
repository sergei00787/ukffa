package com.jbond.ukffa.service.infra.jpa;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class AgLoginServiceImpl implements AgLoginService {

    private final String BASE_URL = "http://212.77.128.19:17201/ServiceJSON/Login";

    @Override
    public Mono<String> getMonoToken(String login, String password) {
        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("UserName", login);
        map.add("Password", password);

        Mono<String> monoToken = WebClient.builder()
                .baseUrl(BASE_URL)
                .build()
                .post()
                .body(BodyInserters.fromMultipartData(map))
                .retrieve()
                .bodyToMono(String.class);
        return monoToken;
    }

    @Override
    public String getToken(String login, String password){
        return getMonoToken(login, password).block();
    }


}
