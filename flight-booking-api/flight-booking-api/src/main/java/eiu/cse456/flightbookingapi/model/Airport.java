package eiu.cse456.flightbookingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "airport")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airport {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "airport_name")
    private String airportName;

    public Airport() {

    }

    public Airport(String airportId, String airportName) {
        this.id = airportId;
        this.airportName = airportName;
    }

    public String getId() {
        return this.id;
    }

    public String getAirportName() {
        return this.airportName;
    }
}
