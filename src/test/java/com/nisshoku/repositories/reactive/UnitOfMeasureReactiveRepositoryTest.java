package com.nisshoku.repositories.reactive;

import com.nisshoku.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    @Autowired
    UnitOfMeasureReactiveRepository uomReactiveRepository;

    @Before
    public void setUp() throws Exception {
        uomReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("UOM");

        uomReactiveRepository.save(unitOfMeasure).block();

        Long count = uomReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void testFindByDescription() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("UOM");

        uomReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetchedUOM = uomReactiveRepository.findByDescription("UOM").block();

        assertNotNull(fetchedUOM);
        assertEquals("UOM", fetchedUOM.getDescription());
    }
}