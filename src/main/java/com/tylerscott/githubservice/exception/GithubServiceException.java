package com.tylerscott.githubservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class GithubServiceException extends RuntimeException {
    private String message;
    public GithubServiceException(String message) {
        this.message = message;
    }
}
