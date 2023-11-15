package com.example.student;

import com.example.student.client.SchoolClient;
import com.example.student.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }

    public ResponseEntity<?> updateStudent(RegisterRequest request, Integer id, HttpServletRequest servletRequest) {
        // Retrieve the original student from the repository
        Optional<Student> studentOptional = repository.findById(id);
        if (studentOptional.isPresent()) {
            Student originalStudent = studentOptional.get();
            String firstName = (request.getFirstname() != null && !request.getFirstname().isEmpty()) ? request.getFirstname() : originalStudent.getFirstname();
            String lastName = (request.getLastname() != null && !request.getLastname().isEmpty()) ? request.getLastname() : originalStudent.getLastname();
            String email = (request.getEmail() != null && !request.getEmail().isEmpty()) ? request.getEmail() : originalStudent.getEmail();
            Integer schoolId = (request.getSchoolId() != null && !request.getSchoolId().toString().isEmpty()) ? request.getSchoolId() : originalStudent.getSchoolId();
            final String authHeader = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);
            School school= client.findSchoolBySchoolId(request.getSchoolId(), authHeader);
            if(school.getId().describeConstable().isPresent()){ //control check for the schoolID
                var updatedStudent = Student.builder()
                        .firstname(firstName)
                        .lastname(lastName)
                        .email(email)
                        .id(id)
                        .schoolId(schoolId)
                        .build();
                repository.save(updatedStudent);
                return ResponseEntity.ok("Student updated");
            }
            else{
                return new ResponseEntity<>("School Id not found", HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>("Student not present", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public void deleteStudentBySchoolId(Integer schoolId){
        repository.deleteBySchoolId(schoolId);
    }
}