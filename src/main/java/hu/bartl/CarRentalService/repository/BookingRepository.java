package hu.bartl.CarRentalService.repository;

import hu.bartl.CarRentalService.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, String> {
}
