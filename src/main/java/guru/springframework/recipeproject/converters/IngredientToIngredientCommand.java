package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.IngredientCommand;
import guru.springframework.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
  private final UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter;

  public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverterCommand) {
    this.uomCommandConverter = uomConverterCommand;
  }

  @Nullable
  @Synchronized
  @Override
  public IngredientCommand convert(Ingredient source) {
    if (source == null) {
      return null;
    }
    final IngredientCommand ingredientCommand = new IngredientCommand();
    ingredientCommand.setId(source.getId());
    ingredientCommand.setAmount(source.getAmount());
    ingredientCommand.setDescription(source.getDescription());
    ingredientCommand.setUom(uomCommandConverter.convert(source.getUom()));
    return ingredientCommand;
  }
}
