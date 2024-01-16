package com.atamertc.repository;

import com.atamertc.repository.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    Boolean existsByUserid(Long id);

    Optional<User> findByAuthid(Long authid);

    Boolean deleteByAuthid(Long authid);
}
