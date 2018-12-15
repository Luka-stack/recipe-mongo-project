package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.RecipeCommand;
import com.nisshoku.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;

    public RecipeToRecipeCommand(final NotesToNotesCommand notesConverter,
                                 final CategoryToCategoryCommand categoryConverter,
                                 final IngredientToIngredientCommand ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe != null) {
            final RecipeCommand recipeCommand = new RecipeCommand();

            recipeCommand.setId(recipe.getId());
            recipeCommand.setDescription(recipe.getDescription());
            recipeCommand.setPrepTime(recipe.getPrepTime());
            recipeCommand.setCookTime(recipe.getCookTime());
            recipeCommand.setServings(recipe.getServings());
            recipeCommand.setSource(recipe.getSource());
            recipeCommand.setUrl(recipe.getUrl());
            recipeCommand.setDirections(recipe.getDirections());
            recipeCommand.setDifficulty(recipe.getDifficulty());
            recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));

            if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
                recipe.getCategories()
                        .forEach(category -> recipeCommand.getCategories()
                        .add(categoryConverter.convert(category)));
            }

            if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
                recipe.getIngredients()
                        .forEach(ingredient -> recipeCommand.getIngredients()
                        .add(ingredientConverter.convert(ingredient)));
            }

            return recipeCommand;
        }
        return null;
    }
}
