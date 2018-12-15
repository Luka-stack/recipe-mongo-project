package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.RecipeCommand;
import com.nisshoku.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(final NotesCommandToNotes notesConverter,
                                 final CategoryCommandToCategory categoryConverter,
                                 final IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand != null) {
            final Recipe recipe = new Recipe();

            recipe.setId(recipeCommand.getId());
            recipe.setDescription(recipeCommand.getDescription());
            recipe.setPrepTime(recipeCommand.getPrepTime());
            recipe.setCookTime(recipeCommand.getCookTime());
            recipe.setServings(recipeCommand.getServings());
            recipe.setSource(recipeCommand.getSource());
            recipe.setUrl(recipeCommand.getUrl());
            recipe.setDirections(recipeCommand.getDirections());
            recipe.setDifficulty(recipeCommand.getDifficulty());
            recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

            if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
                recipeCommand.getCategories()
                        .forEach(category -> recipe.getCategories()
                                .add(categoryConverter.convert(category)));
            }

            if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
                recipeCommand.getIngredients()
                        .forEach(ingredient -> recipe.getIngredients()
                                .add(ingredientConverter.convert(ingredient)));
            }

            return recipe;
        }
        return null;
    }
}
