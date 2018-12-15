package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.UnitOfMeasureCommand;
import com.nisshoku.recipeproject.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        // given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID_VALUE);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        // when
        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        // then
        assertNotNull(unitOfMeasure);
        assertEquals(ID_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}