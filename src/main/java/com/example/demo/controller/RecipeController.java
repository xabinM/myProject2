package com.example.demo.controller;

import com.example.demo.domain.recipe.Recipe;
import com.example.demo.dto.recipe.response.RecipeListResponse;
import com.example.demo.dto.recipe.response.RecipeOneResponse;
import com.example.demo.dto.recipe.request.RecipeRegistryRequest;
import com.example.demo.dto.recipe.request.RecipeUpdateRequest;
import com.example.demo.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    // Create
    @PostMapping("/register")
    public ResponseEntity<?> registerRecipe(@Valid @RequestBody RecipeRegistryRequest request) {
        recipeService.registerRecipe(request);

        return ResponseEntity.noContent().build();
    }

    // Read
    // 1. 전체 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<?> getRecipes() {
        List<Recipe> recipes = recipeService.getRecipes();

        return ResponseEntity.ok(new RecipeListResponse(recipes));
    }

    // 2. 단일 레시피 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipe(id);

        return ResponseEntity.ok(new RecipeOneResponse(recipe));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeUpdateRequest request) {
        recipeService.editRecipe(id, request);

        return ResponseEntity.noContent().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);

        return ResponseEntity.noContent().build();
    }
}
