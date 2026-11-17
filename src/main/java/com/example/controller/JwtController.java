
package com.example.controller;

import com.example.dto.JwtRequest;
import com.example.dto.JwtResponse;
import com.example.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/validate")
    public ResponseEntity<JwtResponse> validateToken(
            @Valid @RequestBody JwtRequest request) {

        try {

            Claims claims = jwtService.validateToken(request.token());

            String username = claims.getSubject();

            List<String> roles = claims.get("roles", List.class);

            JwtResponse response = new JwtResponse(
                    "Token Valid",
                    username,
                    roles
            );

            return ResponseEntity.ok(response);

        } catch (JwtException | IllegalArgumentException e) {

            JwtResponse response = new JwtResponse(
                    "Invalid Token",
                    null,
                    null
            );

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    @GetMapping("/demo-token")
    public ResponseEntity<String> generateDemoToken() {

        return ResponseEntity.ok(jwtService.generateDemoToken());
    }
}
