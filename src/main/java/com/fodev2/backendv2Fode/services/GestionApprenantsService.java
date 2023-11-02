package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CoursResponse;
import com.fodev2.backendv2Fode.dto.StudentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class GestionApprenantsService {

    private final GestionCoursService gestionCoursService = new  GestionCoursService();

    final MoodleConfig moodleConfig = new MoodleConfig();



    public List<StudentResponse[]> getUsers() {
        List<CoursResponse> coursResponses = gestionCoursService.getAllCourses();
        List<StudentResponse[]> students = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_enrol_get_enrolled_users";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&courseid={courseid}";

        for (CoursResponse coursResponse : coursResponses) {

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

        return students;
    }

    public List<StudentResponse[]> getStudents(){

        List<StudentResponse[]> studentResponses = new ArrayList<>();
        List<CoursResponse> coursResponses = gestionCoursService.getAllCourses();
        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_enrol_get_enrolled_users";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&courseid={courseid}";


        ForkJoinPool forkJoinPool = new ForkJoinPool(50);

        try {
            forkJoinPool.submit(() -> coursResponses.parallelStream().forEach(
                    coursResponse -> {
                        ResponseEntity<StudentResponse[]> studentsResponseEntity = restTemplate.getForEntity(
                                requestTemplate,
                                StudentResponse[].class,
                                moodleConfig.wstoken,
                                wsfunction,
                                coursResponse.getId()
                        );
                        synchronized (studentResponses){
                            studentResponses.add(studentsResponseEntity.getBody());
                        }
                    }
            ) ).get();

        }catch (Exception e){
            e.printStackTrace();

        }

        return studentResponses;
    }
}
