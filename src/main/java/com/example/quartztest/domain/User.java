package com.example.quartztest.domain;

import com.example.quartztest.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@EnableAutoConfiguration
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String userName;

    String email;

    @ManyToMany
    @JoinTable(name = "user_friend", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    Set<User> friends;

    @ManyToMany
    @JoinTable(name = "user_games", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))
    Set<Game> games;

    /**
     * Getter of the id attribute
     * @return id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter of the id attribute
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter of the userName attribute
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter of the userName attribute
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter of the email attribute
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email attribute
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of the friends attribute
     * @return friends list
     */
    public Set<User> getFriends() {
        return friends;
    }

    /**
     * Setter of the friends attribute
     * @param friends
     */
    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    /**
     * Getter of the games attribute
     * @return
     */
    public Set<Game> getGames() {
        return games;
    }

    /**
     * Setter of the games attribute
     * @param games
     */
    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public UserDTO toUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.userName);
        userDTO.setEmail(this.email);
        List<Long> friendsIdList = new ArrayList<>();
        this.getFriends().forEach(it->{
            friendsIdList.add(it.id);
        });
        userDTO.setFriends(friendsIdList);
        List<Long> gamesIdList = new ArrayList<>();
        this.getGames().forEach(it->{
            gamesIdList.add(it.id);
        });
        return userDTO;
    }

    public String toUserJSON() {
        Gson gson = new Gson();
        String userJson = gson.toJson(this.toUserDTO());
        return userJson;
    }

}
