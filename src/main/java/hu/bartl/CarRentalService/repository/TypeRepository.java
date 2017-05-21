package hu.bartl.CarRentalService.repository;

import hu.bartl.CarRentalService.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, String> {
}
