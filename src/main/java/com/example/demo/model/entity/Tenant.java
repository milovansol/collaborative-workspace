package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "tenants")
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantId;

    private String name;

    private Long ownerId;

    private Instant createdAt;

    private Instant updatedAt;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<User> users;
}
