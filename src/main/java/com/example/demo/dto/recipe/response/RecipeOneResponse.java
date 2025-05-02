package com.example.demo.dto.recipe.response;

import com.example.demo.domain.recipe.Recipe;
import com.example.demo.dto.recipe.RecipeDto;
import lombok.Getter;

@Getter
public class RecipeOneResponse {
    private RecipeDto recipeDto;

    public RecipeOneResponse(Recipe recipe) {
        this.recipeDto = new RecipeDto(recipe);
    }
}
