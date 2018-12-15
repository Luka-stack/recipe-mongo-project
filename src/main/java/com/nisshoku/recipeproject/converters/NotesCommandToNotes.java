package com.nisshoku.recipeproject.converters;

import com.nisshoku.recipeproject.commands.NotesCommand;
import com.nisshoku.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand != null) {
            final Notes notes = new Notes();

            notes.setId(notesCommand.getId());
            notes.setRecipeNotes(notesCommand.getRecipeNotes());

            return notes;
        }
        return null;
    }
}
