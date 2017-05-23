package hu.bartl.CarRentalService.controller;

import hu.bartl.CarRentalService.dto.BookingDto;
import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.CarResource;
import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import hu.bartl.CarRentalService.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping
    public Collection<CarResource> listCars() {
        Collection<CarShortInfo> carList = carService.getCarList();
        List<CarResource> carResources = carList.stream()
                .map(c -> conversionService.convert(c, CarResource.class))
                .collect(toList());
        carResources.forEach(this::addSelfLink);
        return carResources;
    }

    @GetMapping("/{id}")
    public CarResource findCar(@PathVariable String id) {
        Car car = carService.findCar(id);
        CarResource carResource = conversionService.convert(car, CarResource.class);
        addSelfLink(carResource);
        return carResource;
    }

    @PostMapping("/{id}/bookings")
    @ResponseStatus(CREATED)
    public Car bookCar(@PathVariable String id, @RequestBody BookingDto bookingDto) {
        return carService.bookCar(id, bookingDto);
    }

    private void addSelfLink(CarResource carResource) {
        carResource.add(linkTo(methodOn(CarController.class).findCar(carResource.getCarId())).withSelfRel());
    }
}
