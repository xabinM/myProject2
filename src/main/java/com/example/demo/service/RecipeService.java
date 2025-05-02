package com.example.demo.service;

import com.example.demo.domain.recipe.Recipe;
import com.example.demo.domain.recipe.RecipeMaterials;
import com.example.demo.dto.recipe.request.RecipeRegistryRequest;
import com.example.demo.dto.recipe.request.RecipeUpdateRequest;
import com.example.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public void registerRecipe(RecipeRegistryRequest request) {
        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setImageUrl(request.getImageUrl());
        recipe.setDescription(request.getDescription());
        recipe.setDeleted(false);

        List<RecipeMaterials> materials = request.getMaterials().stream()
                .map(materialName -> {
                    return new RecipeMaterials(materialName, recipe);
                })
                .collect(Collectors.toList());

        recipe.setMaterials(materials);

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

        recipe.setDeleted(true);
    }
}
