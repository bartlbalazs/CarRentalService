package hu.bartl.CarRentalService.controller;

import hu.bartl.CarRentalService.dto.BookingDto;
import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import hu.bartl.CarRentalService.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public Collection<CarShortInfo> listCars() {
        return carService.getCarList();
    }

    @GetMapping("/{id}")
    public Car findCar(@PathVariable String id) {
        return carService.findCar(id);
    }

    @PostMapping("/{id}/bookings")
    @ResponseStatus(CREATED)
    public Car bookCar(@PathVariable String id, @RequestBody BookingDto bookingDto) {
        return carService.bookCar(id, bookingDto);
    }
}
