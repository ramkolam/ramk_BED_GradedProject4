package com.greatlearning.ems.service.impl;

import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.repository.UserRepository;
import com.greatlearning.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public void save(User role) {
        userRepository.save(role);
    }

    @Override
    public User findById(Long roleId) {
        return userRepository.findById(roleId).orElse(null);
    }
}