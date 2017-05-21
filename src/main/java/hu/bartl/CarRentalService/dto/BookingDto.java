package hu.bartl.CarRentalService.dto;

import hu.bartl.CarRentalService.model.Usage;

import java.time.LocalDateTime;

public class BookingDto {

    private LocalDateTime start;
    private LocalDateTime end;
    private Usage usage;
    private String customerName;

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
    public String toString() {
        return "BookingDto{" +
                "start=" + start +
                ", end=" + end +
                ", usage=" + usage +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
