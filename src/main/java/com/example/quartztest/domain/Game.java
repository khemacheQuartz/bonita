package com.example.quartztest.domain;

import com.example.quartztest.dto.GameDTO;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Set;

@Entity
@EnableAutoConfiguration
public class Game {

    /**
     * Id of the game
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    /**
     * Title of the game
     */
    String title;

    /**
     * Cover of the game
     */
    String cover;

    /**
     * Game available in store
     */
    boolean deleted;

    /**
     * Owners of the game
     */
    @ManyToMany
    @JoinTable(name = "app_user_games", joinColumns = @JoinColumn(name = "app_game_id"), inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    Set<User> owners;

    /**
     * Getter of id attribute
     * @return id as Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter of title attribute
     * @return title of the game as String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter of the cover attribute
     * @return cover of the game as String
     */
    public String getCover() {
        return cover;
    }

    /**
     * Setter of the id of the game
     * @param id of the game
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter of the title of the game
     * @param title of the game
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter of the cover of the game
     * @param cover of the game
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * Setter of the owners of the game
     * @return
     */
    public Set<User> getOwners() {
        return owners;
    }

    /**
     * Getter of the owners of the game
     * @param owners
     */
    public void setOwners(Set<User> owners) {
        this.owners = owners;
    }

    /**
     * Getter of the deleted attribute
     * @return
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Setter of the deleted attribute
     * @param deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public GameDTO toGameDTO() {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setCover(this.cover);
        gameDTO.setId(this.id);
        gameDTO.setTitle(this.title);
        return gameDTO;
    }

    public String toGameJSON() {
        Gson gson = new Gson();
        String gameJson = gson.toJson(this.toGameDTO());
        return gameJson;
    }

    public boolean equals(Game game) {
        return this.id==game.id ? true : false;
    }

}
