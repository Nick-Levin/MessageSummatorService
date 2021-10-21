package com.example.messagesummatorservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {

    @LocalServerPort
    private int localPort;

    @Autowired
    private MessageController messageController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("check if MessageController is injectable with all components")
    void isInjectable() {
        assertThat(messageController).isNotNull();
    }

    @Test
    @DisplayName("Check summation algorithm")
    void getSummation() {
        String message1 = "A";
        String number1 = "10";
        String message2 = "B";
        String number2 = "11";

        String expectedResult1 = "{\"msg\":\""+number1+"\"}";
        String expectedResult2 = "{\"msg\":\""+number2+"\"}";

        String url1 = "http://localhost:" + localPort + "/message?message=" + message1 + "&number=" + number1;
        String url2 = "http://localhost:" + localPort + "/message?message=" + message2 + "&number=" + number2;

        assertThat(testRestTemplate.getForObject(url1, String.class)).isEqualTo(expectedResult1);
        assertThat(testRestTemplate.getForObject(url2, String.class)).isEqualTo(expectedResult2);
        assertThat(messageController.getCurrentSummationNumber()).isEqualTo(new BigDecimal(21));
    }

}