package com.nisshoku.recipeproject.repositories;

import com.nisshoku.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
