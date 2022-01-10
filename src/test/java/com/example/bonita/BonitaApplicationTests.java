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
    void allRestaurantNotNullTest(){
        List<Recipe> recipeList = recipeService.getAllRecipes();
        assert (recipeList != null);
    }

    @Test
    void deleteRestaurantTest() {
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
    void createNewRestaurant() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("RestaurantTest");
        recipeService.saveRecipe(newRecipe);
        Recipe savedRecipe = recipeService.getRecipeByName("RestaurantTest");
        assert (savedRecipe != null && "RestaurantTest".equals(savedRecipe.getName()));
    }

}
