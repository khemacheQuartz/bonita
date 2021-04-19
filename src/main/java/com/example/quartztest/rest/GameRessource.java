package com.example.quartztest.rest;

import com.example.quartztest.domain.Game;
import com.example.quartztest.dto.GameDTO;
import com.example.quartztest.services.GameService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameRessource {

    private final GameService gameService;

    public GameRessource(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/api/store/games")
    public ResponseEntity<String> getAllGames() {
        List<Game> gamesList = gameService.getAllGames();
        List<GameDTO> gameDTOList = new ArrayList<>();
        Gson gson = new Gson();
        gamesList.forEach(it-> {
            gameDTOList.add(it.toGameDTO());
        });
        return ResponseEntity.ok(gson.toJson(gameDTOList));
    }

    @PostMapping("/api/store/games")
    public ResponseEntity<Void> createGames(@RequestBody String gameJson) {
        System.out.println("Service de création de jeux");
        Gson gson = new Gson();
        GameDTO gameDTO = gson.fromJson(gameJson,GameDTO.class);
        Game newGame = new Game();
        newGame.setId(gameDTO.getId());
        newGame.setCover(gameDTO.getCover());
        newGame.setTitle(gameDTO.getTitle());
        newGame.setDeleted(false);
        gameService.saveGame(newGame);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/store/games/{gameId}")
    public ResponseEntity<String> getGameById(@PathVariable("gameId") Long id) {
        Game game = gameService.getGameById(id);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(game.toGameDTO()));
    }

    @DeleteMapping("/api/store/games/{gameId}")
    public ResponseEntity<Void> deleteGameById(@PathVariable("gameId") Long id) {
        Game game = gameService.getGameById(id);
        game.setDeleted(true);
        gameService.saveGame(game);
        return ResponseEntity.noContent().build();
    }

}
