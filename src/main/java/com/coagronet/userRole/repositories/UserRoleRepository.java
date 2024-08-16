package com.coagronet.userRole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.userRole.UserRole;
import com.coagronet.userRole.models.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
