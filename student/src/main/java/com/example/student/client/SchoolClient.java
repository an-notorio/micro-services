package com.example.student.client;

import com.example.student.School;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "school-service", url = "${application.config.schools-url}")
public interface SchoolClient {
    @GetMapping("/byId/{school-id}")
    School findSchoolBySchoolId(@PathVariable("school-id") Integer schoolId);
}