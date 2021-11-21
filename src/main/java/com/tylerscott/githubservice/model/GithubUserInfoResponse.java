package com.tylerscott.githubservice.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubUserInfoResponse {

    private String userName;
    private String displayName;
    private String avatar;
    private String geoLocation;
    private String email;
    private String url;
    private LocalDateTime createdAt;
    private RepositoryDetail[] repos;

    public GithubUserInfoResponse(){}
    public GithubUserInfoResponse(UserInfo userInfo, RepositoryDetail[] repos) {
        Objects.requireNonNull(userInfo);
        this.userName = userInfo.getLogin();
        this.displayName = userInfo.getName();
        this.avatar = userInfo.getAvatarUrl();
        this.geoLocation = userInfo.getLocation();
        this.email = userInfo.getEmail();
        this.url = userInfo.getUrl();
        this.createdAt = userInfo.getCreatedAt();
        this.repos = repos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RepositoryDetail[] getRepos() {
        return repos;
    }

    public void setRepos(RepositoryDetail[] repos) {
        this.repos = repos;
    }

    @Override
    public String toString() {
        return "GithubUserInfoResponse{" +
                "userName='" + userName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", geoLocation='" + geoLocation + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", repos=" + Arrays.toString(repos) +
                '}';
    }
}
