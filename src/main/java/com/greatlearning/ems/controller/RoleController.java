package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.Role;
import com.greatlearning.ems.service.RoleService;
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
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping(path = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveRole(@RequestBody Role role) {
        if (role != null) {
            roleService.save(role);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(role.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
