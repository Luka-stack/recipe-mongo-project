package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.IngredientCommand;
import com.nisshoku.recipeproject.commands.UnitOfMeasureCommand;
import com.nisshoku.recipeproject.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Long ID_UOM = new Long(2L);
    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal("1");

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID_UOM);
        ingredientCommand.setUom(unitOfMeasureCommand);

        // when
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(ID_UOM, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        // when
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}