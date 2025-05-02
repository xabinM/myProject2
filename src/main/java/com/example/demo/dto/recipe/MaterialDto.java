package com.example.demo.dto.recipe;

import com.example.demo.domain.recipe.RecipeMaterials;
import lombok.Getter;

@Getter
public class MaterialDto {
    private final Long id;
    private final String name;

    public MaterialDto(RecipeMaterials material) {
        this.id = material.getId();
        this.name = material.getName();
    }
}
