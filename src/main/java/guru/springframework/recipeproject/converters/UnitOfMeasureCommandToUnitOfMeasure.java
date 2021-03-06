package guru.springframework.recipeproject.converters;

import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/** Created by tky on 2022/05/22 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure
    implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
  @Nullable
  @Synchronized
  @Override
  public UnitOfMeasure convert(UnitOfMeasureCommand source) {
    if (source == null) {
      return null;
    }
    final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setDescription(source.getDescription());
    unitOfMeasure.setId(source.getId());
    return unitOfMeasure;
  }
}
