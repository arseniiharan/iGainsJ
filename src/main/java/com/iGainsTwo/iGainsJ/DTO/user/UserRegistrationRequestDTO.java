package com.iGainsTwo.iGainsJ.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegistrationRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    @Length(min = 8, max = 40)
    private String password;

    private int age;

    private int gender;

    private int weight;

    private int height;

}
