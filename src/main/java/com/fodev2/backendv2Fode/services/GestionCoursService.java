package com.fodev2.backendv2Fode.services;

import com.fodev2.backendv2Fode.MoodleConfig;
import com.fodev2.backendv2Fode.dto.CertificatResponse;
import com.fodev2.backendv2Fode.dto.CoursResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service

@Slf4j
@Builder
public class GestionCoursService {
    public GestionCoursService() {
    }

    final MoodleConfig moodleConfig = new MoodleConfig();

    public List<CoursResponse>getAllCourses(){
    RestTemplate restTemplate = new RestTemplate();
    String wsfunction = "core_course_get_courses";


    ResponseEntity<CoursResponse[]> coursResponseResponseEntity = restTemplate.getForEntity(moodleConfig.serveurUrl + "?wstoken={token}&wsfunction={function}&moodlewsrestformat=json", CoursResponse[].class, moodleConfig.wstoken, wsfunction);
    CoursResponse[] coursResponses = coursResponseResponseEntity.getBody();

    return List.of(coursResponses);
}

    public  List<Integer>recupID(List<CoursResponse> coursResponses){
        List<Integer>courseID = new ArrayList<>();
        for(CoursResponse course : coursResponses){
            courseID.add(course.getId());
        }
        return courseID;
    }
}
