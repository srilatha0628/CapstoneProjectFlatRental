package com.capstone.tenantdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.tenantdetails.entity.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    
}