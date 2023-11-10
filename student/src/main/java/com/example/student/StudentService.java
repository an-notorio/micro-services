package com.example.student;

import com.example.student.client.SchoolClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final SchoolClient client;

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }

    public ResponseEntity<?> saveStudent(Student student, HttpServletRequest request){
        try{
            final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            School school= client.findSchoolBySchoolId(student.getSchoolId(), authHeader);
            if(school.getId().describeConstable().isPresent()){
                repository.save(student);
                return ResponseEntity.ok("Student saved");
            }
            else{
                return new ResponseEntity<>("School Id not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception x){
            return new ResponseEntity<>("School Id not found", HttpStatus.BAD_REQUEST);
        }

    }
}