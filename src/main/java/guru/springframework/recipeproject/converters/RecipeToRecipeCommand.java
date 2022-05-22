package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
  private final CategoryToCategoryCommand categoryCommand;
  private final IngredientToIngredientCommand ingredientCommand;
  private final NotesToNotesCommand notesCommand;

  public RecipeToRecipeCommand(
      CategoryToCategoryCommand categoryCommand,
      IngredientToIngredientCommand ingredientCommand,
      NotesToNotesCommand notesCommand) {
    this.categoryCommand = categoryCommand;
    this.ingredientCommand = ingredientCommand;
    this.notesCommand = notesCommand;
  }

  @Nullable
  @Synchronized
  @Override
  public RecipeCommand convert(Recipe source) {
    if (source == null) {
      return null;
    }
    final RecipeCommand recipeCommand = new RecipeCommand();
    recipeCommand.setId(source.getId());
    recipeCommand.setCookTime(source.getCookTime());
    recipeCommand.setDescription(source.getDescription());
    recipeCommand.setDifficulty(source.getDifficulty());
    recipeCommand.setDirections(source.getDirections());
    recipeCommand.setImage(source.getImage());
    recipeCommand.setPrepTime(source.getPrepTime());
    recipeCommand.setServings(source.getServings());
    recipeCommand.setSource(source.getSource());
    recipeCommand.setUrl(source.getUrl());
    recipeCommand.setNotes(notesCommand.convert(source.getNotes()));
    if (source.getCategories() != null && source.getCategories().size() > 0) {
      source
          .getCategories()
          .forEach(
              category -> recipeCommand.getCategories().add(categoryCommand.convert(category)));
    }
    if (source.getIngredients() != null && source.getIngredients().size() > 0) {
      source
          .getIngredients()
          .forEach(
              ingredient ->
                  recipeCommand.getIngredients().add(ingredientCommand.convert(ingredient)));
    }
    return recipeCommand;
  }
}
