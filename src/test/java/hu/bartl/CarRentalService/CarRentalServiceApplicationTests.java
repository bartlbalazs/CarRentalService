package hu.bartl.CarRentalService;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bartl.CarRentalService.model.Booking;
import hu.bartl.CarRentalService.model.Car;
import hu.bartl.CarRentalService.model.Type;
import hu.bartl.CarRentalService.repository.CarRepository;
import hu.bartl.CarRentalService.repository.TypeRepository;
import hu.bartl.CarRentalService.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarRentalServiceApplicationTests {

    private static final String SAMPLE_CAR_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarService carService;

    @Test
    public void shouldListAllCars() throws Exception {
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(100)));
    }

    @Test
    public void shouldReturnDetailedDataForCar() throws Exception {
        mockMvc.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.description", is("Sample description for Car#1")));
    }

    @Test
    @DirtiesContext
    public void shouldBookCarWhenItsFreeToBooking() throws Exception {
        Car car = carRepository.findOne(SAMPLE_CAR_ID);
        Booking booking = bookingAcrossYears(2015, 2016);
        car.getBookings().add(booking);
        booking.setCar(car);
        carRepository.save(car);

        String bookingDtoString = "{\"start\":\"2017-01-01T10:00:00\"," +
                "\"end\":\"2018-02-02T10:00:00\"," +
                "\"usage\":\"DOMESTIC\"," +
                "\"customerName\":\"Sample User\"}";

        mockMvc.perform(post("/api/cars/1/bookings").content(bookingDtoString).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    @DirtiesContext
    public void shouldThrowBadRequestExceptionWhenPreviousBookingIsNotEnded() throws Exception {
        Car car = carRepository.findOne(SAMPLE_CAR_ID);
        Booking booking = bookingAcrossYears(2016, 2018);
        car.getBookings().add(booking);
        booking.setCar(car);
        carRepository.save(car);

        String bookingDtoString = "{\"start\":\"2017-01-01T10:00:00\"," +
                "\"end\":\"2018-02-02T10:00:00\"," +
                "\"usage\":\"DOMESTIC\"," +
                "\"customerName\":\"Sample User\"}";

        mockMvc.perform(post("/api/cars/1/bookings").content(bookingDtoString).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    public void shouldThrowBadRequestExceptionWhenNextBookingIsTooEarly() throws Exception {
        Car car = carRepository.findOne(SAMPLE_CAR_ID);
        Booking previous = bookingAcrossYears(2013, 2014);
        previous.setCar(car);
        Booking next = bookingAcrossYears(2018, 2020);
        next.setCar(car);
        car.getBookings().add(previous);
        car.getBookings().add(next);
        carRepository.save(car);

        String bookingDtoString = "{\"start\":\"2017-01-01T10:00:00\"," +
                "\"end\":\"2019-02-02T10:00:00\"," +
                "\"usage\":\"DOMESTIC\"," +
                "\"customerName\":\"Sample User\"}";

        mockMvc.perform(post("/api/cars/1/bookings").content(bookingDtoString).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DirtiesContext
    public void shouldThrowExceptionWhenForeignUseIsNotPermitted() throws Exception {
        Type unsupportedType = getUnsupportedType();
        Car car = carRepository.findOne(SAMPLE_CAR_ID);
        car.setType(unsupportedType);
        carRepository.save(car);

        String bookingDtoString = "{\"start\":\"2017-01-01T10:00:00\"," +
                "\"end\":\"2019-02-02T10:00:00\"," +
                "\"usage\":\"FOREIGN\"," +
                "\"customerName\":\"Sample User\"}";

        mockMvc.perform(post("/api/cars/1/bookings").content(bookingDtoString).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    private Booking bookingAcrossYears(int startYear, int endYear) {
        Booking booking = new Booking();
        booking.setStart(LocalDateTime.of(startYear, 1, 1, 0, 0));
        booking.setEnd(LocalDateTime.of(endYear, 1, 1, 0, 0));
        return booking;
    }

    private Type getUnsupportedType() {
        Type type = new Type();
        type.setManufacturer("Unsupported Model");
        type.setModel("Unsupported Manufacturer");
        return typeRepository.save(type);
    }
}
