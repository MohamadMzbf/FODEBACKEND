package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.*;
import com.fodev2.backendv2Fode.services.ApprenantServiceBis;
import com.fodev2.backendv2Fode.services.GestionApprenantsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/fodev2/api/apprenent")
//@RequiredArgsConstructor
public class GestionApprenantController {

    private GestionApprenantsService gestionApprenantsService = new GestionApprenantsService();

    private ApprenantServiceBis apprenantServiceBis = new ApprenantServiceBis();

    @GetMapping(path = "/user")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> list(){
        return gestionApprenantsService.getStudents();
    }

    @GetMapping("/gender-count")
    public ApprenantStatistics countStudentsByGender() {
        return gestionApprenantsService.getApprenantStatistics();
    }


    @GetMapping("/membres")
    @ResponseStatus(HttpStatus.OK)
    public CohortMembers getCohortMember(@Param("cohortId") Integer cohortId){

           return  apprenantServiceBis.getCohortsMembers(cohortId);


    }

    @GetMapping("/students")
    public List<StudentResponse> getStudentsByCohort(@RequestParam("cohortId") Integer cohortId) throws Exception {
        return apprenantServiceBis.getStudentsByCohort(cohortId);
    }


    @GetMapping("/user-cohort")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Integer> listStudentsByCohort() {
        return gestionApprenantsService.listStudentsByCohort();
    }






}
