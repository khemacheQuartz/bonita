package com.example.quartztest.repositories;

import com.example.quartztest.domain.Game;
import com.example.quartztest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional <User> findById(Long id);

    List<User> findAll();

    List<User> findAllByGames(Game game);

}
