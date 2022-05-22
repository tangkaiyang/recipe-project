package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand
    implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
  @Nullable
  @Synchronized
  @Override
  public UnitOfMeasureCommand convert(UnitOfMeasure source) {
    if (source == null) {
      return null;
    }
    final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
    unitOfMeasureCommand.setDescription(source.getDescription());
    unitOfMeasureCommand.setId(source.getId());
    return unitOfMeasureCommand;
  }
}
