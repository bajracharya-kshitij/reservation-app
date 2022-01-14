package com.kshitij.reservation.service;

import com.kshitij.reservation.enums.RoleName;
import com.kshitij.reservation.model.Role;

public interface RoleService {

    Role findOrCreate(RoleName roleName);
}
