package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Entities.Student;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student added successfully";
    }

    public Student findStudentById(int studentId) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()) {
            throw new Exception("Incorrect student id");
        }
        Student student = optionalStudent.get();
        return student;
    }
}
