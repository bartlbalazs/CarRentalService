package hu.bartl.CarRentalService.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private String id;

    @ManyToOne
    private Type type;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Booking> bookings;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(type, car.type) &&
                Objects.equals(bookings, car.bookings) &&
                Objects.equals(description, car.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, bookings, description);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", bookings=" + bookings +
                ", description='" + description + '\'' +
                '}';
    }
}
