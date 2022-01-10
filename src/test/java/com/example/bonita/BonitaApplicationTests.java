package com.example.bonita;

import com.example.bonita.domain.Recipe;
import com.example.bonita.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BonitaApplicationTests {

    @Autowired
    private RecipeService recipeService;

    @Test
    void contextLoads() {
    }

    @Test
    void allRecipesNotNullTest(){
        List<Recipe> recipeList = recipeService.getAllRecipes();
        assert (recipeList != null);
    }

    @Test
    void deleteRecipeTest() {
        Recipe recipe = recipeService.getRecipeById(1l);
        recipeService.removeRecipe(recipe);
        List<Recipe> recipeList = recipeService.getAllRecipes();
        recipeList.forEach(currentRestaurant->{
            if(currentRestaurant.getId() == 1l) {
                assert false;
            }
        });
    }

    @Test
    void createNewRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("RecipeTest");
        recipeService.saveRecipe(newRecipe);
        Recipe savedRecipe = recipeService.getRecipeByName("RecipeTest");
        assert (savedRecipe != null && "RecipeTest".equals(savedRecipe.getName()));
    }

}
