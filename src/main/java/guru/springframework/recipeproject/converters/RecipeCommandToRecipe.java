package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
  private final CategoryCommandToCategory category;
  private final IngredientCommandToIngredient ingredient;
  private final NotesCommandToNotes notes;

  public RecipeCommandToRecipe(
      CategoryCommandToCategory category,
      IngredientCommandToIngredient ingredient,
      NotesCommandToNotes notes) {
    this.category = category;
    this.ingredient = ingredient;
    this.notes = notes;
  }

  @Nullable
  @Synchronized
  @Override
  public Recipe convert(RecipeCommand source) {
    if (source == null) {
      return null;
    }
    final Recipe recipe = new Recipe();
    recipe.setId(source.getId());
    recipe.setCookTime(source.getCookTime());
    recipe.setDescription(source.getDescription());
    recipe.setDifficulty(source.getDifficulty());
    recipe.setDirections(source.getDirections());
    recipe.setImage(source.getImage());
    recipe.setPrepTime(source.getPrepTime());
    recipe.setServings(source.getServings());
    recipe.setSource(source.getSource());
    recipe.setUrl(source.getUrl());
    recipe.setNotes(notes.convert(source.getNotes()));
    if (source.getCategories() != null && source.getCategories().size() > 0) {
      source
          .getCategories()
          .forEach(
              categoryCommand -> recipe.getCategories().add(category.convert(categoryCommand)));
    }
    if (source.getIngredients() != null && source.getIngredients().size() > 0) {
      source
          .getIngredients()
          .forEach(
              ingredientCommand ->
                  recipe.getIngredients().add(ingredient.convert(ingredientCommand)));
    }
    return recipe;
  }
}
