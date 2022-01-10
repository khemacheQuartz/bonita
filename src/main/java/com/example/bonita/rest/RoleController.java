package com.example.bonita.rest;

import com.example.bonita.domain.Recipe;
import com.example.bonita.domain.Role;
import com.example.bonita.services.RoleService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/role/{id}")
    public ResponseEntity<String> getRole(@PathVariable("id") Long id) {

        Role role = roleService.getRoleById(id);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(role));
    }



}
