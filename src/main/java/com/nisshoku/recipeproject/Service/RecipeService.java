package com.nisshoku.recipeproject.Service;

import com.nisshoku.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
