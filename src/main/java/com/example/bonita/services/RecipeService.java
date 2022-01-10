package com.example.bonita.services;

import com.example.bonita.domain.Recipe;
import com.example.bonita.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.get();
    }

    public Recipe getRecipeByName(String name) {
        Optional<Recipe> recipe = recipeRepository.findByName(name);
        return recipe.get();
    }


    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void saveRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

}
