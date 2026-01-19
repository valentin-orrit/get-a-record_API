package com.getarecord.getarecord.hello;

import com.getarecord.getarecord.controller.HelloController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    HelloController helloController;

    @Value("${lastfm.api.sharedSecret}")
    private String lastfmSharedSecret;

    private ResponseEntity<String> response;

    @BeforeEach
    void setup() {
        response = restTemplate.getForEntity("/api/hello", String.class);
    }

    @Test
    void contextLoads() {
        assertThat(helloController).isNotNull();
    }

    @Test
    void shouldReturnAHelloString() {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("hello");
    }

    @Test
    void shouldReturnsSharedSecret() {
        assertThat(response.getBody()).contains(lastfmSharedSecret);
    }
}