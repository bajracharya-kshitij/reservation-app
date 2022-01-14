package com.kshitij.reservation.service.impl;

import com.kshitij.reservation.dto.request.UserRequest;
import com.kshitij.reservation.enums.RoleName;
import com.kshitij.reservation.model.User;
import com.kshitij.reservation.repository.UserRepository;
import com.kshitij.reservation.service.RoleService;
import com.kshitij.reservation.service.UserService;
import com.kshitij.reservation.util.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(UserRequest request) throws Exception {
        User user = findByEmail(request.getEmail());
        if (user != null) {
            throw new Exception("User already exists");
        }
        user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(PasswordEncoderUtil.getInstance().encode(request.getPassword()))
                .role(roleService.findOrCreate(RoleName.getEnumByString(request.getRole())))
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
