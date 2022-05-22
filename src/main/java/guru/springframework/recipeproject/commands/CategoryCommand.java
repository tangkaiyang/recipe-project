package guru.springframework.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Created by tky on 2022/05/22 */
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
  private Long id;
  private String description;
}
