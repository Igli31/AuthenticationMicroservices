package com.example.class_service.Service;

import com.example.class_service.DTO.ClassRoomDTO;
import com.example.class_service.Entity.ClassRoom;
import com.example.class_service.Repository.ClassRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassRoomServiceImpl implements ClassRoomService{

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClassRoomDTO createClass(ClassRoomDTO classRoomDTO) {
        ClassRoom classRoom = modelMapper.map(classRoomDTO, ClassRoom.class);
        return modelMapper.map(classRoomRepository.save(classRoom), ClassRoomDTO.class);
    }

    @Override
    public ClassRoomDTO getClassById(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id).get();
        return modelMapper.map(classRoom, ClassRoomDTO.class);
    }

    @Override
    public List<ClassRoomDTO> getAllClasses() {
        return classRoomRepository.findAll().stream()
                .map(classRoom -> modelMapper.map(classRoom, ClassRoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClassRoomDTO updateClass(Long id, ClassRoomDTO classRoomDTO) {
        ClassRoom existingClass =  classRoomRepository.findById(id).get();
        modelMapper.map(classRoomDTO, existingClass);
        ClassRoom updatedClass = classRoomRepository.save(existingClass);
        return modelMapper.map(updatedClass, ClassRoomDTO.class);
    }

    @Override
    public void deleteClassById(Long id) {
        classRoomRepository.deleteById(id);
    }
}
