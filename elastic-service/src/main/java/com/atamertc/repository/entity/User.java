package com.atamertc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(indexName = "user")
public class User extends BaseEntity {

    @Id
    private String id; //UUID
    private Long userid;
    private Long authid;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phone;


}
