package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.CoursResponse;
import com.fodev2.backendv2Fode.services.GestionCoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/fodev2/api/cours")
@RequiredArgsConstructor
public class GestionCoursController {
    private final GestionCoursService gestionCoursService;


    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<CoursResponse> coursResponseList(){
        return  gestionCoursService.getAllCourses();
    }


}
