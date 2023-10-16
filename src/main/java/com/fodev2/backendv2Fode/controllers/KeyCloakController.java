package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.ContentDto;
import com.fodev2.backendv2Fode.dto.UpdateBeneficiaryRequest;
import com.fodev2.backendv2Fode.dto.UpdateKeycloakRequest;
import com.fodev2.backendv2Fode.dto.UserDTO;
import com.fodev2.backendv2Fode.services.KeyCloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class KeyCloakController {
    @Autowired
    KeyCloakService service;
    @PostMapping(path = "/createUser")
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<ContentDto> addUser(@RequestBody UserDTO userDTO){
        service.addUser(userDTO);
        return ResponseEntity.ok(new ContentDto("User Added Successfully."));
    }

    @GetMapping(path = "/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
        List<UserRepresentation> user = service.getUser(userName);
        return user;
    }

    @PutMapping(path = "/update/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContentDto> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateKeycloakRequest updateKeycloakRequest){
//        service.updateUser(userId, userDTO);
        service.updateUser(userId, updateKeycloakRequest);
        return ResponseEntity.ok(new ContentDto("User Details Updated Successfully."));
    }

    @DeleteMapping(path = "/delete/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContentDto> deleteUser(@PathVariable("userId") String userId){
        service.deleteUser(userId);
        return ResponseEntity.ok(new ContentDto("User Deleted Successfully."));
    }

    @GetMapping(path = "/verification-link/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContentDto> sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return ResponseEntity.ok(new ContentDto("Verification Link Send to Registered E-mail Id."));
    }

    @GetMapping(path = "/reset-password/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ContentDto> sendResetPassword(@PathVariable("userId") String userId){
        service.sendResetPassword(userId);
        return ResponseEntity.ok(new ContentDto("Reset Password Link Send Successfully to Registered E-mail Id.")) ;
    }

}
