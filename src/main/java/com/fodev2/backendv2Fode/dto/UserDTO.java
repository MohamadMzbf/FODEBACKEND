package com.fodev2.backendv2Fode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastname;
    private String issuer ="https://raagnale.force-n.sn/realms/FODE_Keyclaok";
    public String clientId = "fk_client";

}
