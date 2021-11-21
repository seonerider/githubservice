package com.tylerscott.githubservice.controller;

import com.tylerscott.githubservice.exception.GithubServiceException;
import com.tylerscott.githubservice.model.GithubUserInfoResponse;
import com.tylerscott.githubservice.model.UserInfo;
import com.tylerscott.githubservice.model.RepositoryDetail;
import com.tylerscott.githubservice.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class GithubServiceController {

    private GithubService githubService;

    @Autowired
    public GithubServiceController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/githubinfo/{username}")
    public ResponseEntity<GithubUserInfoResponse> getGithubData(@PathVariable("username") String username) {
        try {
            UserInfo userInfo = githubService.getUserInfo(username);
            if (Objects.isNull(userInfo)) {
                return ResponseEntity.notFound().build();
            }
            RepositoryDetail[] repositoryDetails = githubService.getRepositoryInfo(username);
            GithubUserInfoResponse response = new GithubUserInfoResponse(userInfo, repositoryDetails);
            return ResponseEntity.ok(response);
        } catch (GithubServiceException exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
