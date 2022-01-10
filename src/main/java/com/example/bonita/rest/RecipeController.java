package com.example.bonita.rest;

import com.example.bonita.domain.Recipe;
import com.example.bonita.dto.RecipeDTO;
import com.example.bonita.services.RecipeService;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/recipe")
    public ResponseEntity<String> getAllRecipes() {
        List<Recipe> recipeList = recipeService.getAllRecipes();
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        Gson gson = new Gson();
        recipeList.forEach(it-> {
            recipeDTOList.add(it.toRecipeDTO());
        });
        return ResponseEntity.ok(gson.toJson(recipeDTOList));
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<String> getRecipeById(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(recipe));
    }

    @PostMapping("/recipe")
    public ResponseEntity<Void> createRecipe(@RequestBody String recipeJson) {
        Gson gson = new Gson();
        RecipeDTO recipeDTO = gson.fromJson(recipeJson, RecipeDTO.class);
        Recipe newRecipe = new Recipe();
        newRecipe.setId(recipeDTO.getId());
        newRecipe.setName(recipeDTO.getName());
        recipeService.saveRecipe(newRecipe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<Void> deleteRestaurantByName(@PathVariable("id") Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        recipeService.removeRecipe(recipe);
        return ResponseEntity.noContent().build();
    }

}
