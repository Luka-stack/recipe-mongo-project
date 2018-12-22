package com.nisshoku.recipeproject.service;

import com.nisshoku.recipeproject.commands.RecipeCommand;
import com.nisshoku.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    void deleteById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
}
