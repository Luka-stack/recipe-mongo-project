package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.RecipeCommand;
import com.nisshoku.recipeproject.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final Long RECIPE_ID = new Long(1L);
    private static final Long CAT_ID_1 = new Long(2L);
    private static final Long CAT_ID_2 = new Long(3L);
    private static final Long INGRED_ID_1 = new Long(4L);
    private static final Long INGRED_ID_2 = new Long(5L);
    private static final Long NOTES_ID = new Long(6L);
    private static final String DESCRIPTION = "description";
    private static final String PREP_TIME = "prep time";
    private static final String COOK_TIME = "cook time";
    private static final Integer SERVINGS = 1;
    private static final String SOURCE = "source";
    private static final String URL = "www.example.com";
    private static final String DIRECTIONS = "directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;

    RecipeToRecipeCommand coverter;

    @Before
    public void setUp() throws Exception {
        coverter = new RecipeToRecipeCommand(new NotesToNotesCommand(), new CategoryToCategoryCommand(),
                                        new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(coverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(coverter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID_2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        // when
        RecipeCommand recipeCommand = coverter.convert(recipe);

        // then
        assertNotNull(recipeCommand);
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }
}