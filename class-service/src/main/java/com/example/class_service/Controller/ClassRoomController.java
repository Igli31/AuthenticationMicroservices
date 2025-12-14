package com.example.class_service.Controller;

import com.example.class_service.DTO.ClassRoomDTO;
import com.example.class_service.Service.ClassRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassRoomController {

    private ClassRoomService classRoomService;

    @PostMapping("/create")
    ResponseEntity<ClassRoomDTO>createClass(@RequestBody ClassRoomDTO classRoomDTO){
        ClassRoomDTO classRoomDTO1 = classRoomService.createClass(classRoomDTO);
        return new ResponseEntity<>(classRoomDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    ResponseEntity<ClassRoomDTO>getClassById(@PathVariable Long id){
        ClassRoomDTO classRoomDTO = classRoomService.getClassById(id);
        return new ResponseEntity<>(classRoomDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<ClassRoomDTO>>getAllClasses(){
        List<ClassRoomDTO> classRoomDTOS = classRoomService.getAllClasses();
        return new ResponseEntity<>(classRoomDTOS, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ClassRoomDTO>updateClass(@PathVariable Long id, @RequestBody ClassRoomDTO classRoomDTO){
        ClassRoomDTO classRoomDTO1 = classRoomService.updateClass(id, classRoomDTO);
        return new ResponseEntity<>(classRoomDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void>deleteClassById(@PathVariable Long id){
        classRoomService.deleteClassById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
