package com.example.rabbitmqtypes.controller;

import com.example.rabbitmqtypes.config.RabbitConfig;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.slf4j.Logger;

@Controller
public class SampleController {

    Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    private final AmqpTemplate template;

    @Autowired
    public SampleController(AmqpTemplate template) {
        this.template = template;
    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody String message) {
        log.info("Emit to myQueue");
        template.convertAndSend("myQueue", message);
        return ResponseEntity.ok("Success emit to queue");
    }
}
