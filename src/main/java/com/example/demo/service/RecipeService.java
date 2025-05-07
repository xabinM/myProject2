package com.example.demo.service;

import com.example.demo.domain.recipe.Recipe;
import com.example.demo.dto.recipe.request.RecipeRegistryRequest;
import com.example.demo.dto.recipe.request.RecipeUpdateRequest;
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
                .orElseThrow(() -> new IllegalArgumentException("레시피가 존재하지 않습니다."));
    }

    @Transactional
    public void editRecipe(Long id, RecipeUpdateRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("레시피가 존재하지 않습니다."));

        recipe.editPartial(request);
    }

    @Transactional
    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("레시피가 존재하지 않습니다."));

        recipe.markDeleted();
    }
}
