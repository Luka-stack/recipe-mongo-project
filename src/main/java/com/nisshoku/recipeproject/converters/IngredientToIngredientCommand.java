package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.IngredientCommand;
import com.nisshoku.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(final UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient != null) {
            final IngredientCommand ingredientCommand = new IngredientCommand();

            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setUom(uomConverter.convert(ingredient.getUom()));

            return ingredientCommand;
        }
        return null;
    }
}
