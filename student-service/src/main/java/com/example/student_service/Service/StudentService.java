package com.example.student_service.Service;

import com.example.student_service.DTO.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(Long studentId);
    List<StudentDTO> getAllStudents();
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudentById(Long studentId);
}
