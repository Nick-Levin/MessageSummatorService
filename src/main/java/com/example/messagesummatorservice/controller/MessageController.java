package com.example.messagesummatorservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/message")
public class MessageController {

    private BigDecimal summationNumber;

    public MessageController() {
        summationNumber = BigDecimal.ZERO;
    }

    @GetMapping
    public ResponseEntity<String> getSummation(
            @RequestParam("message") String message,
            @RequestParam("number") String number
    ) {
        this.summationNumber = this.summationNumber.add(new BigDecimal(number));
        String result = message + " : " + summationNumber.toString();
        System.out.println(result);
        return ResponseEntity.ok("{\"msg\":\""+number+"\"}");
    }

    /**
     * This method is not part of the rest API
     * It works as a getter for testing purposes
     * @return <code>BigDecimal</code>
     */
    public BigDecimal getCurrentSummationNumber() {
        return summationNumber;
    }

}
