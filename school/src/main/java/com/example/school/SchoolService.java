package com.example.school;

import com.example.school.client.StudentClient;
import com.example.school.dto.SchoolDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;
    private final StudentClient client;

    public void saveSchool(SchoolDto school) {
        School schoolToSave = School.builder()
                .name(school.getName())
                .email(school.getEmail())
                .build();
        repository.save(schoolToSave);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId, HttpServletRequest request) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        var students = client.findAllStudentsBySchool(schoolId, authHeader);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
    public School findSchoolById(Integer schoolId) {
        return repository.findById(schoolId).orElse(new School()
        );
    }

    public void updateSchool(Integer schoolId, SchoolDto school){
        repository.save(School.builder()
                        .id(schoolId)
                        .email(school.getEmail())
                        .name(school.getName())
                .build());
    }

    public void deleteSchool(Integer schoolId, HttpServletRequest request) {
        repository.deleteById(schoolId);
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        client.deleteAllStudentsBySchoolId(schoolId, authHeader);
    }
}
