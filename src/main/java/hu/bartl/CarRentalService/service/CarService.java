package hu.bartl.CarRentalService.service;

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
}
