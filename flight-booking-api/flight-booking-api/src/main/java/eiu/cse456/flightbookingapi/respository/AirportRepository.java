package eiu.cse456.flightbookingapi.respository;

import eiu.cse456.flightbookingapi.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query(value = "SELECT a FROM Airport a WHERE a.id = :id")
    Airport findById (String id);
}
