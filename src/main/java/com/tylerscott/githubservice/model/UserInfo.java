package com.tylerscott.githubservice.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfo {

    private String login;
    private String name;
    private String avatarUrl;
    private String location;
    private String email;
    private String url;
    private LocalDateTime createdAt;

    public String getLogin() {
        return login;
    }

    public UserInfo setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public UserInfo setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public UserInfo setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public UserInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserInfo setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt + '\'' +
                '}';
    }
}
