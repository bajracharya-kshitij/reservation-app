package com.kshitij.reservation.service.impl;

import com.kshitij.reservation.enums.RoleName;
import com.kshitij.reservation.model.Role;
import com.kshitij.reservation.repository.RoleRepository;
import com.kshitij.reservation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findOrCreate(RoleName roleName) {
        Role existingRole = roleRepository.findByName(roleName);
        if (existingRole == null) {
            Role role = Role.builder()
                    .name(roleName)
                    .build();
            existingRole = roleRepository.save(role);
        }
        return existingRole;
    }
}
