package com.example.school;

import com.example.school.dto.SchoolDto;
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
            @RequestBody SchoolDto school
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

    @Operation(
            summary = "Update a school",
            description = "Update a school using the ID"
    )
    @PutMapping("update/{school-id}")
    public ResponseEntity<?> updateSchoolById(@PathVariable("school-id") Integer schoolId,
                                                   @RequestBody SchoolDto school){
        service.updateSchool(schoolId,school);
        return new ResponseEntity<>("School updated", HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a school",
            description = "Delete a school and students using the schoolId"
    )
    @DeleteMapping("/delete/{school-id}")
    public ResponseEntity<?> deleteSchoolById(@PathVariable("school-id") Integer schoolId,
                                              HttpServletRequest request){
        service.deleteSchool(schoolId, request);
        return ResponseEntity.ok("School and Student from this school DELETED");
    }
}
