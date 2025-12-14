package com.example.class_service.Service;

import com.example.class_service.DTO.ClassRoomDTO;

import java.util.List;

public interface ClassRoomService {

    ClassRoomDTO createClass(ClassRoomDTO classRoomDTO);
    ClassRoomDTO getClassById(Long id);
    List<ClassRoomDTO> getAllClasses();
    ClassRoomDTO updateClass(Long id, ClassRoomDTO classRoomDTO);
    void deleteClassById(Long id);
}
