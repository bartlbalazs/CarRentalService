package hu.bartl.CarRentalService.conversion;

import hu.bartl.CarRentalService.dto.BookingDto;
import hu.bartl.CarRentalService.model.Booking;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class ConversionServiceConfiguration {

    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new BookingDtoToBookingConverter());
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
}
