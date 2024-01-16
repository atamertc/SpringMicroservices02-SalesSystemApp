package com.atamertc.utility;

//import com.atamertc.manager.ElasticManager;
import com.atamertc.repository.entity.User;
import com.atamertc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestAndRun {
//
//    private final UserService userService;
//
//    private final ElasticManager elasticManager;
//
//
//    //Burada thread kullanarak uygulamanin elastic servisin ayaga kalkma
//    //bagimliligini kaldirdik arkaplanda thread calistiracak
//    //RabbitMq kullansak ta threade gerek kalmadan cozulurdu
//    //1 kere cektikten sonra isimiz bitti ve yoruma aldik
//    @PostConstruct
//    public void init() {
//        saveAllUserToElastic();
//    }
//
//    private void saveAllUserToElastic() {
//        List<User> users = userService.findAll();
//        if (!users.isEmpty()) {
//            users.forEach(elasticManager::addUser);
//        }
//    }



}
