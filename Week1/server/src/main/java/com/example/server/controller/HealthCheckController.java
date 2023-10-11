package com.example.server.controller;

import com.example.server.dto.HealthCheckResponse;
import com.example.server.seminar.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
    @GetMapping("/v1")
    public  Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        // 생성자 사용
        Person person1 = new Person("최", "영린");
        // Builder 패턴 사용
        Person newPerson = Person.builder()
                .lastName("최")
                .firstName("영린")
                .build();
        return response;
    }

    @GetMapping("/v2")
    public ResponseEntity<String> healthCheckV2(){
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/v3")
    public String healthCheckV3() {
        return "ok";
    }

    @GetMapping("/v4")
    public  ResponseEntity<Map<String, String>> healthCheckV4() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v5")
    public ResponseEntity<HealthCheckResponse> healthCheckV5() {
        return ResponseEntity.ok(new HealthCheckResponse());
    }
}
