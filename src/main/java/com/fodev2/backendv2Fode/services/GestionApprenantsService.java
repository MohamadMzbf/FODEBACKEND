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

            if(     coursResponse.getId() == 1 ||
                    coursResponse.getId() == 556 ||
                    coursResponse.getId() == 557 ||
                    coursResponse.getId() == 558 ||
                    coursResponse.getId() == 562 ||
                    coursResponse.getId() == 563 ||
                    coursResponse.getId() == 578 ) continue;

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

        return students;
    }

    public List<StudentResponse[]> getStudents(){

        List<StudentResponse[]> studentResponses = new ArrayList<>();
        List<CoursResponse> coursResponses = gestionCoursService.getAllCourses();
        RestTemplate restTemplate = new RestTemplate();
        String wsfunction = "core_enrol_get_enrolled_users";
        String requestTemplate = moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json&courseid={courseid}";

        List<Integer> ignores = new ArrayList<>();

        ignores.add(1);
        ignores.add(556);
        ignores.add(557);
        ignores.add(558);
        ignores.add(562);
        ignores.add(563);
        ignores.add(578);





        ForkJoinPool forkJoinPool = new ForkJoinPool(50);

        try {
            forkJoinPool.submit(() -> coursResponses.parallelStream().filter(coursResponse -> !ignores.contains(coursResponse.getId())).forEach(
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
                            System.out.println(coursResponse.getId());
                        }
                    }
            ) ).get();

        }catch (Exception e){
            e.printStackTrace();

        }

        return studentResponses;
    }
}
