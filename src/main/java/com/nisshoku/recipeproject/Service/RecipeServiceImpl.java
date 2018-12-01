package com.nisshoku.recipeproject.Service;

import com.nisshoku.recipeproject.domain.Recipe;
import com.nisshoku.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipesSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipesSet::add);
        log.debug("I'm in the service");

        return recipesSet;
    }
}
