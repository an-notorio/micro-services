package com.example.student;

import com.example.student.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "Students", description = "Here you can save and retrieve students")
public class StudentController {

    private final StudentService service;

    @Operation(
            summary = "Save a student",
            description = "Save a student in a school with a 'schoolId' "
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(
            @RequestBody Student student,
            HttpServletRequest request
    ){
        return service.saveStudent(student, request);
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieve a list of all Students"
    )
    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieve a list of all Students in a specific school"
    )
    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<Student>> findAllStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }

    @Operation(
            summary = "Delete a student",
            description = "delete a specific student using ID"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "update a Student",
            description = "update a student with new information"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody RegisterRequest registerRequest, @PathVariable Integer id, HttpServletRequest servletRequest) {
        return service.updateStudent(registerRequest, id, servletRequest);

    }

    @DeleteMapping("/delete/bySchoolId/{school-id}")
    public void deleteAllStudentsBySchoolId(@PathVariable("school-id") Integer schoolId){
        service.deleteStudentBySchoolId(schoolId);
    }
}
