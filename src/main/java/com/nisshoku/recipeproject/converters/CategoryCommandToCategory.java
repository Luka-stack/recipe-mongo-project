package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.CategoryCommand;
import com.nisshoku.recipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category > {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand != null) {
            final Category category = new Category();
            category.setId(categoryCommand.getId());
            category.setDescription(categoryCommand.getDescription());
            return category;
        }
        return null;
    }
}
