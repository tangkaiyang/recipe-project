package guru.springframework.recipeproject.services;


import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import java.util.Set;

/**
 * Created by tky on 2022/05/26
 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
