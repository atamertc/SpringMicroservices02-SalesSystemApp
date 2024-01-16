package com.atamertc.rabbitmq.consumer;

import com.atamertc.rabbitmq.model.RegisterUserModel;
import com.atamertc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserConsumer {

    private final UserService service;

    @RabbitListener(queues = "${rabbitmq.register-queue}")
    public void saveRegisterUserModel(RegisterUserModel model) {
        service.rabbitSaveUser(model);
    }










}