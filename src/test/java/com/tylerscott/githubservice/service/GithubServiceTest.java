package com.tylerscott.githubservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tylerscott.githubservice.config.ObjectMapperConfig;
import com.tylerscott.githubservice.model.RepositoryDetail;
import com.tylerscott.githubservice.model.UserInfo;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class GithubServiceTest {

    private GithubService service;
    private MockWebServer server;
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        service = new GithubService(WebClient.create(String.format("http://localhost:%s",
                server.getPort())));
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void getUserInfo_success() throws JsonProcessingException {
        UserInfo mockUserInfo = new UserInfo()
                .setLogin("tester")
                .setAvatarUrl("http://testing.com")
                .setCreatedAt(LocalDateTime.parse("2011-01-25T18:44:36"))
                .setEmail("test@email.com")
                .setLocation("Earth")
                .setName("Testing Tester")
                .setUrl("http://testing.profile.url.com");

        server.enqueue(new MockResponse().setResponseCode(HttpStatus.OK.value()).setBody(objectMapper.writeValueAsString(mockUserInfo)).addHeader("Content-Type", MediaType.APPLICATION_JSON));
        UserInfo userInfo = service.getUserInfo("tester");
        assertEquals(userInfo.getLogin(), "tester", "Invalid login");
        // continue checks
    }
    @Test
    void getUserInfo_notFound() throws JsonProcessingException {
        server.enqueue(new MockResponse().setResponseCode(HttpStatus.NOT_FOUND.value()).addHeader("Content-Type", MediaType.APPLICATION_JSON));
        UserInfo userInfo = service.getUserInfo("tester");
        assertNull(userInfo,"UserInfo should be null");
        // continue checks
    }

    @Test
    void getRepositoryInfo() throws JsonProcessingException {
        RepositoryDetail mockRepositoryDetail = new RepositoryDetail("test-repo", "http://test-repo-location.com");
        RepositoryDetail mockRepositoryDetail2 = new RepositoryDetail("test-repo2", "http://test-repo-location2.com");
        List<RepositoryDetail> mockRepositoryDetails = new ArrayList<RepositoryDetail>();
        mockRepositoryDetails.add(mockRepositoryDetail);
        mockRepositoryDetails.add(mockRepositoryDetail2);
        server.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(mockRepositoryDetails)).addHeader("Content-Type", MediaType.APPLICATION_JSON));
        RepositoryDetail[] repositoryDetail = service.getRepositoryInfo("tester");
        assertEquals(repositoryDetail.length, 2);
        // continue checks
    }
}