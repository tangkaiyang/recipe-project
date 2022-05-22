package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.NotesCommand;
import guru.springframework.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
  @Nullable
  @Synchronized
  @Override
  public NotesCommand convert(Notes source) {
    if (source == null) {
      return null;
    }
    final NotesCommand notesCommand = new NotesCommand();
    notesCommand.setId(source.getId());
    notesCommand.setRecipeNotes(source.getRecipeNotes());
    return notesCommand;
  }
}
