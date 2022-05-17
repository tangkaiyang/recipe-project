package guru.springframework.recipeproject.repositories;

import guru.springframework.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tky on 2022/05/17
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
