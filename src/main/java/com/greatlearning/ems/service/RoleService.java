package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> list();

    public void save(Role role);

    public Role findById(Long roleId);
}
