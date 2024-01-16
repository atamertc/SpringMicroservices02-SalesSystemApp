package com.atamertc.service;

import com.atamertc.exception.ErrorType;
import com.atamertc.exception.UserManagerException;
//import com.atamertc.manager.ElasticManager;
import com.atamertc.mapper.UserMapper;
import com.atamertc.rabbitmq.model.RegisterUserModel;
import com.atamertc.repository.UserRepository;
import com.atamertc.repository.entity.User;
import com.atamertc.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserService extends ServiceManager<User, Long> {
    private final UserRepository repository;
//    private final ElasticManager elasticManager;

    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

//    public UserService(UserRepository repository, ElasticManager elasticManager) {
//        super(repository);
//        this.repository = repository;
//        this.elasticManager = elasticManager;
//    }

    public Boolean rabbitSaveUser(RegisterUserModel model) {
        if (repository.existsByUsername(model.getUsername())) {
            throw new UserManagerException(ErrorType.USERNAME_EXIST);
        }
        User user = UserMapper.INSTANCE.toUser(model);
        save(user);
//        elasticManager.addUser(user);
        return true;
    }

    @Cacheable(value = "getUpperFunction")
    public String getUpper(String name) {
        //bazi uzun islemler ardindan 3 saniyelik bekleme
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //3 saniye sonra bu caliscak
        return name.toUpperCase(Locale.ENGLISH);
    }

    @CacheEvict(value = "getUpperFunction", allEntries = true)
    public void clearCache() {
        System.out.println("getUpperFunction ile olusan tum cache silindi");
    }







}
