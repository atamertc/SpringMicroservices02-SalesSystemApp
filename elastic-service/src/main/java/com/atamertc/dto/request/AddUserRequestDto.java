package com.atamertc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserRequestDto {
    private Long id;
    private Long authid;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phone;
}
