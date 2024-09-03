package com.coagronet.role.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.role.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
