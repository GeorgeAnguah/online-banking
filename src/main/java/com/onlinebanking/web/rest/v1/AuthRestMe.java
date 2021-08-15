package com.onlinebanking.web.rest.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthRestMe {

    @GetMapping("/api/me")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> me() {
        return ResponseEntity.ok(Collections.singletonMap("name", "testName"));
    }
}
