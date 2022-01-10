package com.example.bonita.repositories;

import com.example.bonita.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

        Optional<User> findById(Long id);

        Optional<User> findByLogin(String name);

        List<User> findAll();

}