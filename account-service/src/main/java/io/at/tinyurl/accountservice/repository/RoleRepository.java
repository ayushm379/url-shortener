package io.at.tinyurl.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.at.tinyurl.accountservice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}
