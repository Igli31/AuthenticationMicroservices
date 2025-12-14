package com.example.student_service.Controller;

import com.example.student_service.DTO.StudentDTO;
import com.example.student_service.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    ResponseEntity<StudentDTO>createStudent(@RequestBody StudentDTO studentDTO){
        StudentDTO studentDTO1 = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(studentDTO1, HttpStatus.CREATED);
    }

    @GetMapping("getById/{studentId}")
    ResponseEntity<StudentDTO>getStudentById(@PathVariable Long studentId){
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<StudentDTO>>getAllStudents(){
        List<StudentDTO> studentDTOS = studentService.getAllStudents();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<StudentDTO>updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    ResponseEntity<Void>deleteStudentById(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
