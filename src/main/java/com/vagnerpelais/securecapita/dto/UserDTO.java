package com.vagnerpelais.securecapita.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private Boolean enabled;
    private Boolean isNotLocked;
    private Boolean isUsingMfa;
    private LocalDateTime createdAt;
}
