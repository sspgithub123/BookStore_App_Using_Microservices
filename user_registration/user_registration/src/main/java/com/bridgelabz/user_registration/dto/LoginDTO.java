package com.bridgelabz.user_registration.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;

    public LoginDTO() {
        super();
    }

}
