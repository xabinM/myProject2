package com.example.demo.dto.recipe;

import com.example.demo.domain.recipe.Recipe;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeDto {
    private final Long id;
    private final String title;
    private final String imageUrl;
    private final String description;
    private final List<MaterialDto> materials;

    public RecipeDto(Recipe recipe) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.imageUrl = recipe.getImageUrl();
        this.description = recipe.getDescription();
        this.materials = recipe.getMaterials().stream()
                .map(MaterialDto::new)
                .collect(Collectors.toList());
    }
}
