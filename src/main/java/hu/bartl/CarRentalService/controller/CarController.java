package hu.bartl.CarRentalService.controller;

import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import hu.bartl.CarRentalService.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public Collection<CarShortInfo> listCarIds() {
        return carService.getCarList();
    }
}
