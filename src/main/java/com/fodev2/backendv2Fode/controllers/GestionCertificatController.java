package com.fodev2.backendv2Fode.controllers;


import com.fodev2.backendv2Fode.dto.CertificatResponse;
import com.fodev2.backendv2Fode.services.GestioncertificatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/fodev2/api/certificats")
@RequiredArgsConstructor
public class GestionCertificatController {


    private final GestioncertificatService gestioncertificatService;

    @GetMapping(path="/lists")
    @ResponseStatus(HttpStatus.OK)
    public List<CertificatResponse> listCertificat(){
        return gestioncertificatService.getListCertificat();

    }

}
