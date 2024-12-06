package com.example.demo.service;

import com.example.demo.model.dto.UserCreateDto;
import com.example.demo.model.entity.Tenant;
import com.example.demo.model.entity.User;
import com.example.demo.model.repo.TenantRepository;
import com.example.demo.model.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TenantRepository tenantRepository;

    public void addUser(UserCreateDto userCreateDto) {
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new IllegalArgumentException("Email existed.");
        }

        User user = new User();
        user.setUserName(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());

        if (userCreateDto.getTenantName() != null) {
            Tenant tenant = tenantRepository.findByName(userCreateDto.getTenantName());
            if (tenant == null) {
                throw new IllegalArgumentException("Tenant not found.");
            }
            user.setTenant(tenant);
        }
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
    }
}
