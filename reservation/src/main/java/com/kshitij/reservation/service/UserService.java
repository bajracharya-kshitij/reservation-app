package com.kshitij.reservation.service;

import com.kshitij.reservation.dto.request.UserRequest;
import com.kshitij.reservation.model.User;

public interface UserService {

    User create(UserRequest request) throws Exception;

    User findByEmail(String email);
}
