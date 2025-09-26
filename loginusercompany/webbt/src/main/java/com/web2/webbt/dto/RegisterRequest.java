package com.web2.webbt.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Long companyId; // optional
    private String role;
}
