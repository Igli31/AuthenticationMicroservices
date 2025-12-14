package com.example.student_service.Service;

import com.example.student_service.DTO.StudentDTO;
import com.example.student_service.Entity.Student;
import com.example.student_service.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        Student createdStudent = studentRepository.save(student);
        return modelMapper.map(createdStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id).get();
        modelMapper.map(studentDTO, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
