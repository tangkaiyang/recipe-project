package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
  private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

  public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
    this.uomConverter = uomConverter;
  }

  @Nullable
  @Synchronized
  @Override
  public Ingredient convert(IngredientCommand source) {
    if (source == null) {
      return null;
    }
    final Ingredient ingredient = new Ingredient();
    ingredient.setId(source.getId());
    ingredient.setAmount(source.getAmount());
    ingredient.setDescription(source.getDescription());
    ingredient.setUom(uomConverter.convert(source.getUom()));
    return ingredient;
  }
}
