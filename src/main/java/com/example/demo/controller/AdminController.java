package com.example.demo.controller;

import com.example.demo.model.dto.TenantCreateDto;
import com.example.demo.model.dto.UserCreateDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.TenantService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private TenantService tenantService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDto user) {
        userService.addUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/create-tenant")
    public ResponseEntity<String> createTenant(@RequestBody TenantCreateDto tenantCreateDto) {
        tenantService.addTenant(tenantCreateDto);
        return ResponseEntity.ok("Tenant created successfully");
    }

    @PutMapping("/update-role/{userId}")
    public ResponseEntity<String> updateUserRole(@PathVariable String userId, @RequestParam String role) {
        return ResponseEntity.ok("User role updated successfully");
    }
}
