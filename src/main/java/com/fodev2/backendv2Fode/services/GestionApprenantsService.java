package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CoursResponse;
import com.fodev2.backendv2Fode.dto.StudentResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class GestionApprenantsService {

    private final GestionCoursService gestionCoursService = new  GestionCoursService();

    final MoodleConfig moodleConfig = new MoodleConfig();



    public List<StudentResponse> getUsers() {
        List<CoursResponse> coursResponses = gestionCoursService.getAllCourses();
        List<StudentResponse[]> students = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_enrol_get_enrolled_users";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&courseid={courseid}";


        for (CoursResponse coursResponse : coursResponses) {

            if(coursResponse.getCategoryid() == 11 || coursResponse.getCategoryid() == 9) continue;

            System.out.println(coursResponse.getId());


            try {
                ResponseEntity<StudentResponse[]> studentsResponseEntity = restTemplate.getForEntity(
                        requestTemplate,
                        StudentResponse[].class,
                        moodleConfig.wstoken,
                        wsfunction,
                        coursResponse.getId()
                );
                StudentResponse[] studentsResponseEntityBody = studentsResponseEntity.getBody();
                students.add(studentsResponseEntityBody);
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        }

        return cleaningList(students);
    }

    public List<StudentResponse> getStudents(){

        List<StudentResponse[]> studentResponses = new ArrayList<>();
        List<CoursResponse> coursResponses = gestionCoursService.getAllCourses();
        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_enrol_get_enrolled_users";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&courseid={courseid}";

       // coursResponses = coursResponses.stream().filter(coursResponse -> coursResponse.getCategoryid() != 11).collect(Collectors.toList());


        try {
             coursResponses.parallelStream().filter(coursResponse -> coursResponse.getCategoryid() != 11).forEach(
                    coursResponse -> {
                            //coursesID.add(coursResponse.getCategoryid());
                            ResponseEntity<StudentResponse[]> studentsResponseEntity = restTemplate.getForEntity(
                                    requestTemplate,
                                    StudentResponse[].class,
                                    moodleConfig.wstoken,
                                    wsfunction,
                                    coursResponse.getId()
                            );
                            studentResponses.add(studentsResponseEntity.getBody());

                    }
            );

        }catch (Exception e){
            e.printStackTrace();

        }

        //return  coursesID;
        return cleaningList(studentResponses);
    }

    public List<StudentResponse> cleaningList(List<StudentResponse[]> studentResponses) {

        final List<StudentResponse> cleanList = new ArrayList<>();

        studentResponses.forEach(studentResponses1 ->
                Arrays.stream(studentResponses1).toList().forEach(studentResponse -> {

                    if (cleanList.isEmpty()) {
                        cleanList.add(studentResponse);
                    }else{
                        if(cleanList.stream().noneMatch(p -> p.getId().equals(studentResponse.getId()))){
                            cleanList.add(studentResponse);
                        }
                    }


                })
        );
        System.out.println(cleanList.size());
        return cleanList;



//        for (StudentResponse[] studentResponses1 : studentResponses) {
//
//            for (StudentResponse studentResponse : studentResponses1) {
//                if (cleanList.isEmpty()) {
//                    cleanList.add(studentResponse);
//                } else {
//                    if (cleanList.stream().noneMatch(p -> p.getId().equals(studentResponse.getId()))) {
//                        cleanList.add(studentResponse);
//                    }
//                }
//
//            }
//        }

    }


//       studentResponses.forEach(studentResponses1 -> {
//
//         Arrays.stream(studentResponses1).toList().forEach(studentResponse -> {
//             System.out.println(studentResponse.getId());
//             if (cleanList.isEmpty())  {
//                 cleanList.add(studentResponse);
//             }else {
//                 for (StudentResponse clean : cleanList) {
//                     if (!Objects.equals(clean.getId(), studentResponse.getId())) {
//                         cleanList.add(studentResponse);;
//                     }
//                 }
//             }
//
//
//         });
//
//
//         //cleanList.add((StudentResponse) tempSet);
//       });








}
