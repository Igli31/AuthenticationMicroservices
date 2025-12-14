package com.example.student_service.Service;

import com.example.student_service.Configuration.RestTemplateConfig;
import com.example.student_service.DTO.ClassRoomDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ClassServiceClient {

    private final RestTemplate restTemplate;

    public ClassServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClassRoomDTO fetchClassById(Long classId){
        return restTemplate.getForObject("http://class-service/api/classes/getById/" + classId, ClassRoomDTO.class);
    }
}
