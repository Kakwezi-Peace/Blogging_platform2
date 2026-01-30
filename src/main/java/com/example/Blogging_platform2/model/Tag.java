package com.example.Blogging_platform2.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tag {
    private int id;

    @NotBlank(message = "Tag name is required")
    private String name;

}