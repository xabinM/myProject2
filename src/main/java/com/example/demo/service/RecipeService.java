package com.example.demo.service;

import com.example.demo.domain.recipe.Recipe;
import com.example.demo.dto.recipe.request.RecipeRegistryRequest;
import com.example.demo.dto.recipe.request.RecipeUpdateRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public void registerRecipe(RecipeRegistryRequest request) {
        Recipe recipe = new Recipe(
                request.getTitle(),
                request.getImageUrl(),
                request.getDescription(),
                request.getMaterials()
        );

        recipeRepository.save(recipe);
    }

    public List<Recipe> getRecipes() {

        return recipeRepository.findAll();
    }

    public Recipe getRecipe(Long id) {

        return recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.RECIPE_NOT_EXIST_EXCEPTION.getMessage()));
    }

    @Transactional
    public void editRecipe(Long id, RecipeUpdateRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.RECIPE_NOT_EXIST_EXCEPTION.getMessage()));

        recipe.editPartial(request);
    }

    @Transactional
    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Exception.RECIPE_NOT_EXIST_EXCEPTION.getMessage()));

        recipe.markDeleted();
    }
}
