package guru.springframework.recipeproject.repositories;

import guru.springframework.recipeproject.domain.Category;
import guru.springframework.recipeproject.domain.Ingredient;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tky on 2022/05/17
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
