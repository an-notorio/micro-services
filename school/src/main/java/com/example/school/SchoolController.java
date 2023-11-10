package com.example.school;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
@Tag(name = "Schools" , description = "Here you can save, retrieve and get more info on school")
public class SchoolController {

    private final SchoolService service;

    @Operation(
            summary = "Save a school",
            description = "Save a school in the DB"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestBody School school
    ) {
        service.saveSchool(school);
    }

    @Operation(
            summary = "Get all schools",
            description = "Retrieve a list of all schools"
    )
    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieve a list of all Students in a specific school"
    )
    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllSchools(
            @PathVariable("school-id") Integer schoolId,
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId, request));
    }

    @Operation(
            summary = "Search a school",
            description = "Retrieve a school using the ID"
    )
    @GetMapping("/byId/{school-id}")
    public ResponseEntity<School> findSchoolById(@PathVariable("school-id") Integer schoolId){

        return ResponseEntity.ok(service.findSchoolById(schoolId));
    }
}
