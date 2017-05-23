package hu.bartl.CarRentalService.service;

import hu.bartl.CarRentalService.client.UsagePermissionServiceClient;
import hu.bartl.CarRentalService.dto.BookingDto;
import hu.bartl.CarRentalService.exception.BadRequestException;
import hu.bartl.CarRentalService.exception.ResourceNotFoundException;
import hu.bartl.CarRentalService.model.Booking;
import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.Usage;
import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import hu.bartl.CarRentalService.repository.BookingRepository;
import hu.bartl.CarRentalService.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class CarService {

    public static final PageRequest TOP = new PageRequest(0, 1);
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UsagePermissionServiceClient usagePermissionServiceClient;

    @Autowired
    private ConversionService conversionService;

    public Collection<CarShortInfo> getCarList() {
        return carRepository.findBy();
    }

    public Car findCar(String id) {
        return carRepository.findByCarId(id).orElseThrow(() -> new ResourceNotFoundException("Car with id: " + id + "not found."));
    }

    public Car bookCar(String carId, BookingDto bookingDto) {
        Car car = findCar(carId);
        if (bookingDto.getUsage() == Usage.FOREIGN && usagePermissionServiceClient.isForeignUsagePermitted(car.getType())) {
            throw new BadRequestException("Car#" + carId + "is not allowed to book for foreign usage.");
        }

        if (previousBookingIsNotEnded(carId, bookingDto.getStart())) {
            throw new BadRequestException("Car#" + carId + "is not available on the selected date.");
        }

        if (nextBookingIsTooEarly(car, bookingDto.getEnd())) {
            throw new BadRequestException("Car#" + carId + "is not available on the selected date.");
        }

        Booking booking = conversionService.convert(bookingDto, Booking.class);
        booking.setCar(car);
        car.getBookings().add(booking);
        carRepository.save(car);
        return car;
    }

    private boolean previousBookingIsNotEnded(String carId, LocalDateTime start) {
        List<Booking> previousBookings = bookingRepository.findBookingBefore(carId, start, TOP);
        if (previousBookings.size() == 0l) {
            return false;
        }
        return previousBookings.get(0).getEnd().isAfter(start);
    }

    private boolean nextBookingIsTooEarly(Car car, LocalDateTime end) {
        return false;
    }
}
