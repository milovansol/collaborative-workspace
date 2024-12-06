package com.example.demo.service;

import com.example.demo.model.dto.TenantCreateDto;
import com.example.demo.model.entity.Tenant;
import com.example.demo.model.repo.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public void addTenant(TenantCreateDto tenantCreateDto) {
        if (tenantRepository.findByName(tenantCreateDto.getName()) != null) {
            throw new IllegalArgumentException("Tenant existed.");
        }

        Tenant tenant = new Tenant();
        tenant.setName(tenantCreateDto.getName());
        tenant.setCreatedAt(Instant.now());
        tenant.setUpdatedAt(Instant.now());

        tenantRepository.save(tenant);
    }
}
