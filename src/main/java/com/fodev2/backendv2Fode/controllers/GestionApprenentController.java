package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.CertificatResponse;
import com.fodev2.backendv2Fode.dto.EducationRequest;
import com.fodev2.backendv2Fode.services.GestionApprenantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/fodev2/api/apprenent")
@RequiredArgsConstructor
public class GestionApprenentController {

    private GestionApprenantsService gestionApprenantsService = new GestionApprenantsService();

    @GetMapping(path = "/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> list(){
        return gestionApprenantsService.getUser();
    }
}
