package com.example.rabbitmqtypes.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
       return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue1(){
        return new Queue("myQueue1");
    }

    @Bean
    public Queue myQueue2(){
        return new Queue("myQueue2");
    }

    //FANOUT
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout");
    }


    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueue2()).to(fanoutExchange());
    }

    //DIRECT
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct");
    }
    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("warning");
    }

    @Bean
    public Binding binding4(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("info");
    }

    @Bean
    public Binding binding5(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("error");
    }


    //TOPIC
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic");
    }

    @Bean
    public Binding binding6(){
        return BindingBuilder.bind(myQueue1()).to(topicExchange()).with("one.*");
    }

    @Bean
    public Binding binding7(){
        return BindingBuilder.bind(myQueue2()).to(topicExchange()).with("two.*");
    }


//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("myQueue");
//        container.setMessageListener(message -> log.info("Received from myQueue: "+ new String(message.getBody())));
//        return container;
//    }
}
