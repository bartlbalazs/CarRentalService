package hu.bartl.CarRentalService.repository;

import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends CrudRepository<Car, String> {

    List<CarShortInfo> findBy();

    Optional<Car> findByCarId(String id);
}
