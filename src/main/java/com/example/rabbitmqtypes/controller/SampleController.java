package com.example.rabbitmqtypes.controller;

import com.example.rabbitmqtypes.config.RabbitConfig;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.slf4j.Logger;

@Controller
public class SampleController {

    Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    private final RabbitTemplate template;

    @Autowired
    public SampleController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody String message) {
        log.info("Emit to myQueue");
        template.setExchange("fanout");
        template.convertAndSend( message);
        return ResponseEntity.ok("Success emit to queue");
    }
}
