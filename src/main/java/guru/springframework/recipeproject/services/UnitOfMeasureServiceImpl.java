package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.commands.UnitOfMeasureCommand;
import guru.springframework.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipeproject.domain.UnitOfMeasure;
import guru.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

/** Created by tky on 2022/05/26 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

  public UnitOfMeasureServiceImpl(
      UnitOfMeasureRepository unitOfMeasureRepository,
      UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
    this.unitOfMeasureRepository = unitOfMeasureRepository;
    this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
  }

  @Override
  public Set<UnitOfMeasureCommand> listAllUoms() {
    return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
        .map(unitOfMeasureToUnitOfMeasureCommand::convert)
        .collect(Collectors.toSet());
  }
}
