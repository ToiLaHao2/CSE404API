package eiu.cse456.flightbookingapi.respository;


import eiu.cse456.flightbookingapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT f FROM Flight f WHERE f.departureAirportId = :departureAirportId AND f.arrivalAirportId = :arrivalAirportId AND departureDate = :departureDate")
    List<Flight> findByRule(String departureAirportId, String arrivalAirportId, Date departureDate);

    @Query(value = "SELECT f FROM Flight f  WHERE f.id = :id")
    Flight findById(int id);
}
