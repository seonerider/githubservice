package com.tylerscott.githubservice.service;

import com.tylerscott.githubservice.exception.GithubServiceException;
import com.tylerscott.githubservice.model.UserInfo;
import com.tylerscott.githubservice.model.RepositoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GithubService {
    private final WebClient webClient;

    @Autowired
    public GithubService(WebClient webClient) {
        this.webClient = webClient;
    }

    public GithubService(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public UserInfo getUserInfo(String username) throws GithubServiceException {
        return webClient
                .get()
                .uri( "/users/{username}", username)
                .retrieve()
                .onStatus(HttpStatus::isError, this::handleError)
                .bodyToMono(UserInfo.class).block();

    }

    public RepositoryDetail[] getRepositoryInfo(String username) throws GithubServiceException  {
        return webClient
                .get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatus::isError, this::handleError)
                .bodyToMono(RepositoryDetail[].class).block();
    }

    private Mono<GithubServiceException> handleError(ClientResponse clientResponse) {
        return clientResponse.bodyToMono(String.class)
                .flatMap(errorBody ->
                    clientResponse.rawStatusCode() == HttpStatus.NOT_FOUND.value() ?
                            Mono.empty() :
                            Mono.error(new GithubServiceException("Error status Code: " + clientResponse.statusCode())));
    }
}
