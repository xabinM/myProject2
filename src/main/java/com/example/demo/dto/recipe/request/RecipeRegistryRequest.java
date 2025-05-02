package com.example.demo.dto.recipe.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeRegistryRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 5, max = 50, message = "제목은 5자 이상 50자 이하로 입력해주세요.")
    private String title;

    @NotBlank(message = "사진 URL을 입력해주세요.")
    private String imageUrl;

    @NotBlank(message = "설명을 입력해주세요.")
    @Size(max = 1000, message = "설명은 1000자 이하로 입력해주세요.")
    private String description;

    @NotEmpty(message = "재료를 한 가지 이상 입력해주세요.")
    private List<String> materials;
}
