package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CoursResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GestionApprenantsService {

    private final GestionCoursService gestionCoursService = new  GestionCoursService();

    final MoodleConfig moodleConfig = new MoodleConfig();



    public List<Object[]> getUser(){
     //  List<CoursResponse>  coursResponses = gestionCoursService.getAllCourses();
       List<Object[]> students = new ArrayList<Object[]>();
        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_enrol_get_enrolled_users";
        ResponseEntity<Object[]> studentsResponseEntity = restTemplate.getForEntity(moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&courseid={couseid}", Object[].class, moodleConfig.wstoken, wsfunction, 5);
        Object[] studentsResponseEntityBody = studentsResponseEntity.getBody();
        students.add(studentsResponseEntityBody);

//       for(CoursResponse coursResponse : coursResponses){
//
//       }

       return students;


    }
}
