package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.IngredientCommand;
import com.nisshoku.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomcConverter;

    public IngredientCommandToIngredient(final UnitOfMeasureCommandToUnitOfMeasure uomcConverter) {
        this.uomcConverter = uomcConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if (ingredientCommand != null) {
            final Ingredient ingredient = new Ingredient();

            ingredient.setId(ingredientCommand.getId());
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setUom(uomcConverter.convert(ingredientCommand.getUom()));

            return ingredient;
        }
        return null;
    }
}
