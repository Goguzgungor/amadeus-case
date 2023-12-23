package com.example.amadeuscase.auth.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;


    @NotNull(message = "Password cannot be blank")
    private String password;


    @NotNull(message = "Role cannot be null")
    private Role role;


    public UserCreateDto(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
