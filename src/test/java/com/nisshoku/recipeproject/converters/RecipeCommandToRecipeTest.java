package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.CategoryCommand;
import com.nisshoku.recipeproject.commands.IngredientCommand;
import com.nisshoku.recipeproject.commands.NotesCommand;
import com.nisshoku.recipeproject.commands.RecipeCommand;
import com.nisshoku.recipeproject.domain.Difficulty;
import com.nisshoku.recipeproject.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    RecipeCommandToRecipe coverter;

    @Before
    public void setUp() throws Exception {
        coverter = new RecipeCommandToRecipe(new NotesCommandToNotes(), new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(coverter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(coverter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID_2);
        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGRED_ID_1);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGRED_ID_2);
        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        // when
        Recipe recipe = coverter.convert(recipeCommand);

        // then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(URL, recipe.getUrl());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}