package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.User;
import com.greatlearning.ems.service.RoleService;
import com.greatlearning.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/api")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        if (user != null && user.getRoles() != null && user.getRoles().size() == 1) {
            long roleId = user.getRoles().get(0).getId();
            user.getRoles().clear();
            user.getRoles().add(roleService.findById(roleId));
            userService.save(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
