package com.atamertc.service;

import com.atamertc.dto.request.LoginRequestDto;
import com.atamertc.dto.request.RegisterRequestDto;
import com.atamertc.dto.response.TokenResponseDto;
import com.atamertc.exception.AuthManagerException;
import com.atamertc.exception.ErrorType;
import com.atamertc.manager.UserManager;
import com.atamertc.mapper.AuthMapper;
import com.atamertc.rabbitmq.producer.RegisterUserProducer;
import com.atamertc.repository.AuthRepository;
import com.atamertc.repository.entity.Auth;
import com.atamertc.utility.JwtTokenManager;
import com.atamertc.utility.ServiceManager;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final AuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final UserManager userManager;
    private final RegisterUserProducer registerUserProducer;

    public AuthService(AuthRepository repository, JwtTokenManager jwtTokenManager, UserManager userManager, RegisterUserProducer registerUserProducer) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.userManager = userManager;
        this.registerUserProducer = registerUserProducer;
    }


    public Auth registerUser(RegisterRequestDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.REGISTER_USERNAME_EXIST);
        }
        Auth auth = AuthMapper.INSTANCE.toAuth(dto);
        save(auth);
        registerUserProducer.registerUser(AuthMapper.INSTANCE.toRegisterUserModel(auth));
        return auth;
    }

    public TokenResponseDto doLogin(LoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USERNAME_OR_PASSWORD_INCORRECT);
        }
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        return new TokenResponseDto(token.get());
    }

    public List<Auth> findAllAuth(String token) {
        Optional<Long> id = jwtTokenManager.getIdFromToken(token);
        if (id.isEmpty()) {
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }
        return findAll();
    }


}
