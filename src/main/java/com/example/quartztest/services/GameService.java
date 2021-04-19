package com.example.quartztest.services;

import com.example.quartztest.domain.Game;
import com.example.quartztest.domain.User;
import com.example.quartztest.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.get();
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public void removeGame(Game game) {
        gameRepository.delete(game);
    }

}
