package com.example.quartztest.repositories;

import com.example.quartztest.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game,Long>, JpaSpecificationExecutor<Game> {

    Optional<Game> findById(Long id);

    List<Game> findAll();


}
