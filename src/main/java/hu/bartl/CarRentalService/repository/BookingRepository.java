package hu.bartl.CarRentalService.repository;

import hu.bartl.CarRentalService.model.Booking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, String> {

    @Query(("select b from Booking b where b.car.carId = ?1 and b.start <= ?2 order by b.start desc"))
    List<Booking> findBookingBefore(String carId, LocalDateTime date, Pageable pageable);
}
