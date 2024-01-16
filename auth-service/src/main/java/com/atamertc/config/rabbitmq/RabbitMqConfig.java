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

    //************** EXCHANGE FIELD AND BEAN **************//
    //------------- AuthService Exchange -------------//
    @Value("${rabbitmq.auth-exchange}")
    private String authExchange;

    @Bean
    DirectExchange authExchange() {
        return new DirectExchange(authExchange);
    }

    //******************* PRODUCER FIELDS AND BEANS *******************//


    //------------- AuthService Register Metodu -------------//
    @Value("${rabbitmq.register-queue}")
    private String registerQueueName;
    @Value("${rabbitmq.register-bindingKey}")
    private String registerBindingKey;

    @Bean
    Queue registerQueue() {
        return new Queue(registerQueueName);
    }
    @Bean
    public Binding bindingRegister(final Queue registerQueue, final DirectExchange authExchange) {
        return BindingBuilder.bind(registerQueue).to(authExchange).with(registerBindingKey);
    }



    //******************* CONSUMER FIELDS AND BEANS *******************//








}
