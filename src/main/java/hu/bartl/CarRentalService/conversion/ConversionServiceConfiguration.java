package hu.bartl.CarRentalService.conversion;

import hu.bartl.CarRentalService.dto.BookingDto;
import hu.bartl.CarRentalService.model.Booking;
import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.CarResource;
import hu.bartl.CarRentalService.model.projection.CarShortInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.ArrayList;

@Configuration
public class ConversionServiceConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new BookingDtoToBookingConverter());
        conversionService.addConverter(new CarToCarResourceConverter());
        conversionService.addConverter(new CarShortInfoToCarResourceConverter());
        return conversionService;
    }

    private class BookingDtoToBookingConverter implements Converter<BookingDto, Booking> {

        @Override
        public Booking convert(BookingDto bookingDto) {
            Booking booking = new Booking();
            booking.setStart(bookingDto.getStart());
            booking.setEnd(bookingDto.getEnd());
            booking.setUsage(bookingDto.getUsage());
            booking.setCustomerName(bookingDto.getCustomerName());
            return booking;
        }
    }

    private class CarToCarResourceConverter implements Converter<Car, CarResource> {

        @Override
        public CarResource convert(Car car) {
            CarResource carResource = new CarResource();
            carResource.setCarId(car.getCarId());
            carResource.setDescription(car.getDescription());
            carResource.setType(car.getType());
            carResource.setBookings(new ArrayList<>(car.getBookings()));
            return carResource;
        }
    }

    private class CarShortInfoToCarResourceConverter implements Converter<CarShortInfo, CarResource> {

        @Override
        public CarResource convert(CarShortInfo car) {
            CarResource carResource = new CarResource();
            carResource.setCarId(car.getCarId());
            carResource.setType(car.getType());
            return carResource;
        }
    }
}
