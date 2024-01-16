package com.atamertc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
    @NotBlank(message = "Kullanici adi bos birakilamaz.")
    @Size(min = 4, max = 20)
    String username;
    @NotBlank(message = "Sifre adi bos birakilamaz.")
    @Size(min = 8, max = 40)
    String password;
}
