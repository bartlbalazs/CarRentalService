package hu.bartl.CarRentalService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    private String bookingId;

    @ManyToOne
    private Car car;
    private LocalDateTime start;
    private LocalDateTime end;
    private Usage usage;
    private String customerName;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) &&
                Objects.equals(car, booking.car) &&
                Objects.equals(start, booking.start) &&
                Objects.equals(end, booking.end) &&
                usage == booking.usage &&
                Objects.equals(customerName, booking.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, car, start);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", car=" + car +
                ", start=" + start +
                ", end=" + end +
                ", usage=" + usage +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
