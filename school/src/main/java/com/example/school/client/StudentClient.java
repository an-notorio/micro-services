package com.example.school.client;

import com.example.school.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {

    @GetMapping("/school/{school-id}")
    List<Student> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId,
                                          @RequestHeader("Authorization") String token);

    @DeleteMapping("/delete/bySchoolId/{school-id}")
    void deleteAllStudentsBySchoolId(@PathVariable("school-id") Integer schoolId,
                                     @RequestHeader("Authorization") String token);
}
