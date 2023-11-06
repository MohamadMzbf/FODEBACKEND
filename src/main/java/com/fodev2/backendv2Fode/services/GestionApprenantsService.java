package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CoursResponse;
import com.fodev2.backendv2Fode.dto.StudentResponse;
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

        return cleaningList(students);
    }

    public List<StudentResponse> getStudents(){

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
            forkJoinPool.submit(() -> coursResponses.parallelStream().filter(coursResponse -> ignores.contains(coursResponse.getId())).forEach(
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


        return cleaningList(studentResponses);
    }

    public List<StudentResponse> cleaningList(List<StudentResponse[]> studentResponses){

        final List<StudentResponse> cleanList = new ArrayList<>();

        for(StudentResponse[] studentResponses1 : studentResponses){
            Arrays.stream(studentResponses1).toList().forEach(studentResponse -> {
                        System.out.println(studentResponse.getId());

                        if (cleanList.isEmpty()) {
                            cleanList.add(studentResponse);
                        }else{
                            if(cleanList.stream().noneMatch(p -> p.getId().equals(studentResponse.getId()))){
                                cleanList.add(studentResponse);
                            }
                        }


                    });
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

        return cleanList;
    }

    public static <T> Set<T> mergeSet(Set<T> a, Set<T> b) {

        // Adding all elements of respective Sets
        // using addAll() method
        return new HashSet<T>() {
            {
                addAll(a);
                addAll(b);
            }
        };
    }


}
