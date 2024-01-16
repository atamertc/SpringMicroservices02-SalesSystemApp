package com.atamertc.service;

import com.atamertc.dto.request.AddUserRequestDto;
import com.atamertc.dto.request.PagingRequestDto;

import com.atamertc.mapper.UserMapper;
import com.atamertc.repository.UserRepository;
import com.atamertc.repository.entity.User;
import com.atamertc.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService extends ServiceManager<User, String> {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void saveUserElastic(AddUserRequestDto dto) {
        if (!repository.existsByUserid(dto.getId())) {
            save(UserMapper.INSTANCE.toUser(dto));
        }
    }

    public Optional<User> findByAuthid(Long authid) {
        return repository.findByAuthid(authid);
    }

    public Page<User> findAllPage(PagingRequestDto dto) {
        //Siralama ve sayfalama icin bize gerekli nesneler
        Pageable pageable = null;
        Sort sort = null;

        if (dto.getSortParameter() != null) {
            String direction = dto.getDirection() == null ? "ASC" : dto.getDirection();
            sort = Sort.by(Sort.Direction.fromString(direction), dto.getSortParameter());
        }

        Integer pageSize = dto.getPageSize() == null ? 10 :
                dto.getPageSize() < 1 ? 10 : dto.getPageSize();

        //hem siralama hem sayfalama secildiyse
        if (sort != null && dto.getCurrentPage() != null) {
            pageable = PageRequest.of(dto.getCurrentPage(), pageSize, sort);
        }
        //siralama yok ama sayfalama secildiyse
        else if (sort == null && dto.getCurrentPage() != null) {
            pageable = PageRequest.of(dto.getCurrentPage(), pageSize);
        }
        //siralama ve sayfalama yok
        else {
            pageable = PageRequest.of(0, pageSize);
        }
        return repository.findAll(pageable);
    }


    public Boolean deleteByAuthid(Long authid) {
        return repository.deleteByAuthid(authid);
    }
}
