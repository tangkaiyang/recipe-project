package guru.springframework.recipeproject.commands;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Created by tky on 2022/05/22 */
@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {
  private Long id;
  private Long recipeId;
  private String description;
  private BigDecimal amount;
  private UnitOfMeasureCommand uom;
}
