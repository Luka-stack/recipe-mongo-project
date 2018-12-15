package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.IngredientCommand;
import com.nisshoku.recipeproject.domain.Ingredient;
import com.nisshoku.recipeproject.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long ID_UOM = new Long(2L);
    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal("1");

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() throws Exception {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_UOM);
        ingredient.setUom(unitOfMeasure);

        // when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(ID_UOM, ingredientCommand.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        // when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
    }
}