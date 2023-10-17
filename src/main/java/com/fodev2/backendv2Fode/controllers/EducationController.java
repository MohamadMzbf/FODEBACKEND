package com.fodev2.backendv2Fode.controllers;

import com.fodev2.backendv2Fode.dto.EducationRequest;
import com.fodev2.backendv2Fode.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/fodev2/api/education")
@RequiredArgsConstructor
public class EducationController {

//    @Autowired
    private  final EducationService educationService;

    @PostMapping("/createERducation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createEducation(@RequestBody EducationRequest educationRequest){
        educationService.createEducation(educationRequest);
    }

}
