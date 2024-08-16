package com.coagronet.role.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
