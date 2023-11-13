package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.ApprenantStatistics;
import com.fodev2.backendv2Fode.dto.CertificatResponse;
import com.fodev2.backendv2Fode.dto.EducationRequest;
import com.fodev2.backendv2Fode.dto.StudentResponse;
import com.fodev2.backendv2Fode.services.GestionApprenantsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/fodev2/api/apprenent")
//@RequiredArgsConstructor
public class GestionApprenantController {

    private GestionApprenantsService gestionApprenantsService = new GestionApprenantsService();

    @GetMapping(path = "/user")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> list(){
        return gestionApprenantsService.getStudents();
    }

    @GetMapping("/gender-count")
    public ApprenantStatistics countStudentsByGender() {
        return gestionApprenantsService.getApprenantStatistics();
    }


    @GetMapping("/user-cohort")
    @ResponseStatus(HttpStatus.OK)
    public Map<Integer, Integer> listStudentsByCohort() {
        return gestionApprenantsService.listStudentsByCohort();
    }



    @GetMapping("/id-cohort")
    @ResponseStatus(HttpStatus.OK)
    public Integer[] idCohorte(){
        return gestionApprenantsService.getCohortIds();
    }


}
