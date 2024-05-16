package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.User;

import java.util.List;

public interface UserService {

    List<User> list();

    public void save(User user);

    public User findById(Long userId);

}
