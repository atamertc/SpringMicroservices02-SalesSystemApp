package com.atamertc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotBlank(message = "Kullanici adi bos birakilamaz.")
    @Size(min = 4, max = 20)
    String username;
    @Email(message = "Lutfen gecerli bir e-mail formati giriniz.")
    String email;
    @NotBlank(message = "Sifre adi bos birakilamaz.")
    @Size(min = 8, max = 40)
    String password;
}
