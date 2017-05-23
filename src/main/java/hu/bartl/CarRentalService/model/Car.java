package hu.bartl.CarRentalService.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private String carId;

    @ManyToOne
    private Type type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId) &&
                Objects.equals(type, car.type) &&
                Objects.equals(bookings, car.bookings) &&
                Objects.equals(description, car.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, type, bookings, description);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", type=" + type +
                ", bookings=" + bookings +
                ", description='" + description + '\'' +
                '}';
    }
}
