package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.Normalizer;
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

    public ApprenantStatistics getApprenantStatistics() {

        List<StudentResponse> students = getStudents();

        ApprenantStatistics apprenantStatistics = new ApprenantStatistics();

        List<ApprenantZone> apprenantZones = new ArrayList<>();

        for (StudentResponse student : students) {
            // Utilisez la méthode getGenderFromCustomFields pour obtenir le genre
            String gender = getGenderFromCustomFields(student);
            apprenantStatistics.setTotalApprenant(apprenantStatistics.getTotalApprenant()+1);

            if(student.getCity() != null){
                String city = student.getCity().toUpperCase().trim();
                String normalizedWord = Normalizer.normalize(city, Normalizer.Form.NFD);
                city = normalizedWord.replaceAll("\\p{M}","");


                if (apprenantZones.isEmpty()){
                    ApprenantZone apprenantZone = new ApprenantZone();
                    apprenantZone.setZone(city);
                    apprenantZone.setNombreEtudiant(1);
                    apprenantZones.add(apprenantZone);
                }else{
                    String finalCity = city;
                    if(apprenantZones.stream().noneMatch(p -> p.getZone().equals(finalCity))){
                        ApprenantZone apprenantZone = new ApprenantZone();
                        apprenantZone.setZone(city);
                        apprenantZone.setNombreEtudiant(1);
                        apprenantZones.add(apprenantZone);
                    }else{
                        ApprenantZone apprenantZone = new ApprenantZone();
                        int i ;
                        int nombreTotal=0;
                        for(i =0 ; i < apprenantZones.size() ; i++){
                            if(apprenantZones.get(i).getZone().equals(city)){
                                nombreTotal = apprenantZones.get(i).getNombreEtudiant();
                                break;
                            }
                        }
                        apprenantZones.get(i).setNombreEtudiant(nombreTotal+1);
                    }
                }
            }

            // Vérifiez le genre et mettez à jour le compteur correspondant
            if ("masculin".equalsIgnoreCase(gender)) {
                apprenantStatistics.setApprenantMasculin(apprenantStatistics.getApprenantMasculin()+1);
            } else if ("feminin".equalsIgnoreCase(gender)) {

                apprenantStatistics.setApprenantFeminin(apprenantStatistics.getApprenantFeminin()+1);
            }else{

                apprenantStatistics.setGenreNonDefini(apprenantStatistics.getGenreNonDefini()+1);
            }
        }

        apprenantStatistics.setApprenantZones(apprenantZones);

        return apprenantStatistics;

      //  return genderCount;
    }


    // Méthode pour extraire le genre à partir du champ "customfields"
    private String getGenderFromCustomFields(StudentResponse student) {

        List<CustomsField> customFieldList1 = student.getCustomfields();
        if (customFieldList1 != null){
            for (CustomsField customsField : customFieldList1) {
                if ("Genre".equalsIgnoreCase(customsField.getName())) {
                    return customsField.getValue();
                }
            }
        }else{
            return null;
        }
        return null;
    }



    //Cette methode permet de eliminer les doublons
    public List<StudentResponse> cleaningList(List<StudentResponse[]> studentResponses) {

        final List<StudentResponse> cleanList = new ArrayList<>();

        studentResponses.forEach(studentResponses1 ->
                Arrays.stream(studentResponses1).toList().forEach(studentResponse -> {

                    System.out.println(studentResponse.getId());

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
