package com.fodev2.backendv2Fode.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test")
public class RolesAdminController {

    @GetMapping(path = "/HelloWord")
    public ResponseEntity<String> getAllProfile(){
        return new ResponseEntity<>("Hello Word", HttpStatus.OK);
    }
//    @GetMapping("/admin-FODE/resource")
//    @PreAuthorize("hasRole('admin-FODE')")
//    public String adminFODEResource() {
//        return "This resource is for admin-FODE";
//    }
//
//    @GetMapping("/admin-FCC/resource")
//    @PreAuthorize("hasRole('admin-FCC')")
//    public String adminFCCResource() {
//        return "This resource is for admin-FCC";
//    }




//    @GetMapping("/hello-2")
//    public String hello2(){
//        return  "Hello from spring and keycloack";
//    }


}
