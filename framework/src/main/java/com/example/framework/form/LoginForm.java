package com.example.framework.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "{NotEmpty_ID}")
    private String loginId;

    @NotEmpty(message = "{NotEmpty_PASS}")
    private String password;
}
