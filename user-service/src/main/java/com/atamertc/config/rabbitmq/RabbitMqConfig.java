package com.atamertc.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    //******************* EXCHANGE FIELD AND BEAN *******************//
    //------------- UserService Exchange -------------//
    @Value("${rabbitmq.user-exchange}")
    private String userExchange;

    @Bean
    DirectExchange userExchange() {
        return new DirectExchange(userExchange);
    }


    //******************* PRODUCER FIELDS AND BEANS *******************//




    //******************* CONSUMER FIELDS AND BEANS *******************//


    //------------- AuthService Register Metodu -------------//
    @Value("${rabbitmq.register-queue}")
    private String registerQueueName;

    @Bean
    Queue registerQueue() {
        return new Queue(registerQueueName);
    }










}
