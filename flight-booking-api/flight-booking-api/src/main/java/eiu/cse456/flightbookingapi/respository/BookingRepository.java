package eiu.cse456.flightbookingapi.respository;

import eiu.cse456.flightbookingapi.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "SELECT b FROM Booking b WHERE b.id = :id")
    Booking findById(int id);

    @Query(value = "SELECT b FROM Booking b WHERE b.passengerEmail = :passengerEmail")
    ArrayList<Booking> getByRule(String passengerEmail);
}
