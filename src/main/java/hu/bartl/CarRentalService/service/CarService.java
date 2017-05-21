package hu.bartl.CarRentalService.service;

import hu.bartl.CarRentalService.exception.ResourceNotFoundException;
import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import hu.bartl.CarRentalService.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Collection<CarShortInfo> getCarList() {
        return carRepository.findBy();
    }

    public Car findCar(String id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id: " + id + "not found."));
    }
}
