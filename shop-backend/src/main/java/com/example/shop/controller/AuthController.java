package com.example.shop.controller;

import com.example.shop.model.User;
import com.example.shop.security.JwtUtil;
import com.example.shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User found = userService.findByUsername(user.getUsername());
        if (found != null) {
            String token = jwtUtil.generateToken(found.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).build();
    }
}
