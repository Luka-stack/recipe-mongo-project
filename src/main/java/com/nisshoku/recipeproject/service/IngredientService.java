package com.nisshoku.recipeproject.service;

import com.nisshoku.recipeproject.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long IngredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
