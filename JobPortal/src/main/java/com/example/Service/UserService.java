package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Entity.User;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User getById(int id) {

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "User Not Found" ));
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user != null &&passwordEncoder.matches(password,user.getPassword())) {

            return user;
        }

        return null;
    }
}