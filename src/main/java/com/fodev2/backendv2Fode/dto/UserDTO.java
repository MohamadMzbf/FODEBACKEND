package com.fodev2.backendv2Fode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastname;
}
