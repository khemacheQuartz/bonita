package com.example.bonita.domain;

import com.example.bonita.dto.RecipeDTO;
import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@EnableAutoConfiguration
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private User author;

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String title) {
        this.name = title;
    }


    public RecipeDTO toRecipeDTO() {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(this.id);
        recipeDTO.setName(this.name);
        return recipeDTO;
    }

    public String toRecipeJson() {
        Gson gson = new Gson();
        return gson.toJson(this.toRecipeDTO());
    }

   public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean equals(Recipe recipe) {
        return Objects.equals(this.id, recipe.id);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
