package com.nisshoku.recipeproject.repositories;

import com.nisshoku.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
