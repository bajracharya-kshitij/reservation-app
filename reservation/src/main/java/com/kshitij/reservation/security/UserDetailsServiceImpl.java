package com.kshitij.reservation.security;

import com.kshitij.reservation.model.User;
import com.kshitij.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = Optional.ofNullable(userService.findByEmail(email));
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with email : " + email);
        }

        return UserPrincipal.build(user.get());
    }
}
