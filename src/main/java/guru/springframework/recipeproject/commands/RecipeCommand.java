package guru.springframework.recipeproject.commands;

import guru.springframework.recipeproject.domain.Difficulty;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Created by tky on 2022/05/22 */
@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
  private Long id;
  private String description;
  private Integer cookTime;
  private Difficulty difficulty;
  private String directions;
  private Byte[] image;
  private Integer prepTime;
  private Integer servings;
  private String source;
  private String url;
  private Set<IngredientCommand> ingredients = new HashSet<>();
  private NotesCommand notes;
  private Set<CategoryCommand> categories = new HashSet<>();
}
