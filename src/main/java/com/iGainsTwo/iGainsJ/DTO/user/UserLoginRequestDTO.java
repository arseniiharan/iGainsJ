package com.iGainsTwo.iGainsJ.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequestDTO {
    @Email
    @NotBlank
    String email;

    @NotBlank
    @Length(min=8, max=40)
    String password;
}
