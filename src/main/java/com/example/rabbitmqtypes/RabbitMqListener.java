package com.example.rabbitmqtypes;

import com.example.rabbitmqtypes.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitMqListener {
    Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    @RabbitListener(queues = "myQueue1")
    public void processMyQueue(String message){
        log.info("Received from first myQueue: " + message);
    }

    @RabbitListener(queues = "myQueue2")
    public void processMyQueue2(String message){
        log.info("Received from second myQueue: " + message);
    }


}
