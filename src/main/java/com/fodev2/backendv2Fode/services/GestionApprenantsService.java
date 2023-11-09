package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.ApprenantStatistics;
import com.fodev2.backendv2Fode.dto.CoursResponse;
import com.fodev2.backendv2Fode.dto.CustomField;
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

    // cette methode permet de récupérer les apprenants deja netoyei au doublons de manière parallèle
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

//    public Map<String, Integer> countStudentsByGender() {
//        List<StudentResponse> students = getStudents();
//
//        // Utilisez un Map pour stocker le compte des apprenants par sexe
//        Map<String, Integer> genderCount = new HashMap<>();
//        genderCount.put("Masculin", 0);
//        genderCount.put("Féminin", 0);
//
//        for (StudentResponse student : students) {
//            // Utilisez la méthode getGenderFromCustomFields pour obtenir le genre
//            String gender = getGenderFromCustomFields(student);
//
//            // Vérifiez le genre et mettez à jour le compteur correspondant
//            if ("masculin".equalsIgnoreCase(gender)) {
//                genderCount.put("Masculin", genderCount.get("Masculin") + 1);
//            } else if ("féminin".equalsIgnoreCase(gender)) {
//                genderCount.put("Féminin", genderCount.get("Féminin") + 1);
//            }
//        }
//
//        return genderCount;
//    }


    // Méthode pour extraire le genre à partir du champ "customfields"
//    private String getGenderFromCustomFields(StudentResponse student) {
//
//        List<CustomField> customFieldList1 = student.getCustomFieldList();
//        for (CustomField customField : customFieldList1) {
//            if ("Genre".equalsIgnoreCase(customField.getName())) {
//                return customField.getValue();
//            }
//        }
//        return "Champs non renseignei";
//    }



    //Cette methode permet de eliminer les doublons
    public List<StudentResponse> cleaningList(List<StudentResponse[]> studentResponses) {

        final List<StudentResponse> cleanList = new ArrayList<>();

        studentResponses.forEach(studentResponses1 ->
                Arrays.stream(studentResponses1).toList().forEach(studentResponse -> {

                    if (cleanList.isEmpty()) {
                        cleanList.add(studentResponse);
                    } else {
                        if (cleanList.stream().noneMatch(p -> p.getId().equals(studentResponse.getId()))) {
                            cleanList.add(studentResponse);
                        }
                    }

                })
        );

        return cleanList;
    }

}
