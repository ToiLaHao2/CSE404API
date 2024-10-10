package eiu.cse456.flightbookingapi.respository;

import eiu.cse456.flightbookingapi.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query("SELECT p FROM Passenger p WHERE p.email =:email")
    Passenger findByEmail(String email);

    Passenger findById(int id);

}
