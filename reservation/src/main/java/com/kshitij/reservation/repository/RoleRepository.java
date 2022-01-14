package com.kshitij.reservation.repository;

import com.kshitij.reservation.enums.RoleName;
import com.kshitij.reservation.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName roleName);
}
