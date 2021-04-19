package com.example.quartztest.rest;

import com.example.quartztest.domain.Game;
import com.example.quartztest.domain.User;
import com.example.quartztest.dto.GameDTO;
import com.example.quartztest.dto.UserDTO;
import com.example.quartztest.services.GameService;
import com.example.quartztest.services.UserService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class UserRessources {

    private final UserService userService;
    private final GameService gameService;

    public UserRessources(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<String> getAllUsers() {

        Gson gson = new Gson();
        List<UserDTO> usersDTOList = new ArrayList<>();
        userService.getAllUsers().forEach(it->{
            usersDTOList.add(it.toUserDTO());
        });

        return ResponseEntity.ok(gson.toJson(usersDTOList));
    }

    @PostMapping("/api/users")
    public ResponseEntity<Void> createUsers(@RequestBody String userJson) {
        System.out.println("Service de création d'utilisateurs");
        Gson gson = new Gson();
        UserDTO userDTO = gson.fromJson(userJson,UserDTO.class);
        User newUser = new User();
        newUser.setId(userDTO.getId());
        newUser.setUserName(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        userService.saveUser(newUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(userService.getUserById(id).toUserJSON());
    }

    @GetMapping("/api/users/{userId}/games")
    public ResponseEntity<String> getGamesByUserId(@PathVariable("userId") Long id) {
        List<GameDTO> userGames = new ArrayList<>();
        Gson gson = new Gson();
        userService.getUserById(id).getGames().forEach(it-> {
            userGames.add(it.toGameDTO());
        });
        return ResponseEntity.ok(gson.toJson(userGames));
    }

    @PostMapping("/api/users/{userId}/games")
    public ResponseEntity<Void> addGamesByUserId(@PathVariable("userId") Long userId, @RequestBody String gamesIdListJson) {
        List<GameDTO> gameIdList = new ArrayList<>();
        Gson gson = new Gson();
        User user = userService.getUserById(userId);

        List<String> gamesIdList = gson.fromJson(gamesIdListJson,ArrayList.class);
        gamesIdList.forEach(it-> {
            user.getGames().add(gameService.getGameById(Long.parseLong(it)));
        });
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/users/{userId}/games/{gameId}")
    public ResponseEntity<Void> removeGameByUserId(@PathVariable("userId") Long userId, @PathVariable("gameId") Long gameId) {
        System.out.println("Service de suppression des jeu à un utilisateur");
        User user = userService.getUserById(userId);
        Game gameToRemove = gameService.getGameById(gameId);
        Set<Game> userGames = user.getGames();
        userGames.remove(gameToRemove);
        user.setGames(userGames);
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/users/{userId}")
    public ResponseEntity<Void> removeUserById(@PathVariable("userId") Long userId) {
        System.out.println("Service de suppression d'utilisateur");
        User user = userService.getUserById(userId);
        userService.removeUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/users/{userId}/friends")
    public ResponseEntity<String> getFriendsByUserId(@PathVariable("userId") Long id) {
        List<UserDTO> userFriends = new ArrayList<>();
        Gson gson = new Gson();
        userService.getUserById(id).getFriends().forEach(it-> {
            userFriends.add(it.toUserDTO());
        });
        return ResponseEntity.ok(gson.toJson(userFriends));
    }

    @PostMapping("/api/users/{userId}/friends")
    public ResponseEntity<String> createFriendsByUserId(@PathVariable("userId") Long id, @RequestBody String friendIdJson) {
        List<UserDTO> userFriends = new ArrayList<>();
        Gson gson = new Gson();
        User user = userService.getUserById(id);

        String friendId = gson.fromJson(friendIdJson,String.class);
        User friend = userService.getUserById(Long.parseLong(friendId));
        user.getFriends().add(friend);
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/users/{userId}/friends/{friendId}")
    public ResponseEntity<Void> removeFriendsById(@PathVariable("userId") Long userId, @PathVariable("friendId") Long friendId) {
        System.out.println("Service de suppression d'amis");
        User user = userService.getUserById(userId);
        User friendToRemove = userService.getUserById(friendId);
        user.getFriends().remove(friendToRemove);
        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

}
