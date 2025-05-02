package com.example.demo.domain.recipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecipeMaterials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeMaterials() {
    }

    public RecipeMaterials(String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}
