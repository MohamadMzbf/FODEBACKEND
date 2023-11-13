package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CohortMembers;
import com.fodev2.backendv2Fode.dto.StudentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class ApprenantServiceBis {

    final MoodleConfig moodleConfig = new MoodleConfig();
    final RestTemplate restTemplate = new RestTemplate();

    public CohortMembers getCohortsMembers(Integer cohortId) {

        String wsfunction = "core_cohort_get_cohort_members";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&cohortids[]={cohortId}";
        List<CohortMembers> membersCohort = new ArrayList<>();

        try{

            ResponseEntity<CohortMembers[]> cohortsMembers = restTemplate.getForEntity(
                    requestTemplate,
                    CohortMembers[].class,
                    moodleConfig.wstoken,
                    wsfunction,
                    cohortId
            );

             membersCohort = List.of(cohortsMembers.getBody());


        }catch (Exception e){

              System.out.println(e.getMessage());
        }

        return membersCohort.get(0);

    }

    public List<StudentResponse> getStudentsByCohort(Integer cohortId) throws Exception{
        CohortMembers cohortMembers = getCohortsMembers(cohortId);
        List<StudentResponse> studentResponses = new ArrayList<>();

        String wsfunction = "core_user_get_users_by_field";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&field=id&values[]={value}";


        if(cohortMembers!=null){

           try {
               cohortMembers.getUserids().parallelStream().forEach(userId -> {
                   ResponseEntity<StudentResponse[]> studentResponseResponseEntity = restTemplate.getForEntity(
                           requestTemplate,
                           StudentResponse[].class,
                           moodleConfig.wstoken,
                           wsfunction,
                           userId
                   );

                   List<StudentResponse> studentResponses1 = List.of(studentResponseResponseEntity.getBody());
                   studentResponses.add(studentResponses1.get(0));

               });

               return studentResponses;

           }catch (Exception e){
               throw new Exception(e.getMessage());
           }


        }
        return studentResponses;
    }
}
