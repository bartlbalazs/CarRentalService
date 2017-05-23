package hu.bartl.CarRentalService.model;


import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class CarResource extends ResourceSupport {

    private String carId;
    private Type type;
    private List<Booking> bookings;
    private String description;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "CarResource{" +
                "carId='" + carId + '\'' +
                ", type=" + type +
                ", bookings=" + bookings +
                ", description='" + description + '\'' +
                '}';
    }
}
