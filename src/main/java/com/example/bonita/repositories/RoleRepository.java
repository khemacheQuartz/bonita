package com.example.bonita.repositories;

import com.example.bonita.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

    List<Role> findAll();

}
