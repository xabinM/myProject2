package com.example.demo.dto.recipe.response;

import com.example.demo.domain.recipe.Recipe;
import com.example.demo.dto.recipe.RecipeDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecipeListResponse {
    private final List<RecipeDto> recipes;

    public RecipeListResponse(List<Recipe> recipes) {
        List<RecipeDto> temp = new ArrayList<>();
        for (Recipe recipe : recipes) {
            RecipeDto recipeDto = new RecipeDto(recipe);
            temp.add(recipeDto);
        }

        this.recipes = temp;
    }
}
