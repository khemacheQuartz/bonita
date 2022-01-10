package com.example.bonita.services;

import com.example.bonita.domain.Role;
import com.example.bonita.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }

    public Role getRoleByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        return role.get();
    }


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public void removeRole(Role role) {
        roleRepository.delete(role);
    }

}
