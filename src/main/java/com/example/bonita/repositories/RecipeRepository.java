package com.example.bonita.repositories;

import com.example.bonita.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>, JpaSpecificationExecutor<Recipe> {

    Optional<Recipe> findById(Long id);

    Optional<Recipe> findByName(String name);

    List<Recipe> findAll();

}
