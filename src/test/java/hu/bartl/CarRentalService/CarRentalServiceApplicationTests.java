package hu.bartl.CarRentalService;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.bartl.CarRentalService.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void shouldBookCar() throws Exception {
        String bookingDtoString = "{\"start\":\"2017-02-02T10:00:00\"," +
                "\"end\":\"2018-02-02T10:00:00\"," +
                "\"usage\":\"DEOMESTIC\"," +
                "\"customerName\":\"Sample User\"}";

        mockMvc.perform(post("/api/cars/1/bookings").content(bookingDtoString).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap());
    }
}
