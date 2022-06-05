package guru.springframework.recipeproject.commands;

import guru.springframework.recipeproject.domain.Difficulty;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

/** Created by tky on 2022/05/22 */
@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {
  private Long id;
  @NotBlank
  @Size(min=3, max=255)
  private String description;
  @Min(1)
  @Max(999)
  private Integer cookTime;
  private Difficulty difficulty;
  @NotBlank
  private String directions;
  private Byte[] image;
  @Min(1)
  @Max(999)
  private Integer prepTime;
  @Min(1)
  @Max(100)
  private Integer servings;
  private String source;
  @URL
  @NotBlank
  private String url;
  private Set<IngredientCommand> ingredients = new HashSet<>();
  private NotesCommand notes;
  private Set<CategoryCommand> categories = new HashSet<>();
}
