package com.nisshoku.recipeproject.controllers;

import com.nisshoku.recipeproject.Service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    private String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("I'm in Index Controller Method getIndexPage");

        return "index";
    }
}
