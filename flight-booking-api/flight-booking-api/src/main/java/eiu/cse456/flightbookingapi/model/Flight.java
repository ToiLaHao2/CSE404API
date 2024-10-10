package eiu.cse456.flightbookingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalTime;

@Entity
@Table(name = "flight")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @PrimaryKeyJoinColumn
    @Column(name = "departure_airport_id")
    private String departureAirportId;
    @PrimaryKeyJoinColumn
    @Column(name = "arrival_airport_id")
    private String arrivalAirportId;
    @Column(name = "departure_date")
    private Date departureDate;
    @Column(name = "departure_time")
    private LocalTime departureTime;
    @Column(name = "arrival_date")
    private Date arrivalDate;
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
    @Column(name = "aircraft_type")
    private String aircraftType;
    @Column(name = "ticket_price")
    private Integer ticketPrice;
    @Column(name = "duration")
    private String duration;
    @Column(name = "number_of_stops")
    private Integer numberOfStops;
    @Column(name = "seat_class")
    private String seatClass;
    @Column(name = "baggage_regulations")
    private String baggageRegulations;
    @Column(name = "ancillary_services")
    private String ancillaryServices;

    public Flight() {

    }

    public Flight(Integer id, String departureAirportId, String arrivalAirportId, Date departureDate, Date arrivalDate, LocalTime departureTime, LocalTime arrivalTime, String aircraftType, Integer ticketPrice, String duration, Integer numberOfStops, String seatClass, String baggageRegulations, String ancillaryServices) {
        this.id = id;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraftType = aircraftType;
        this.ticketPrice = ticketPrice;
        this.duration = duration;
        this.numberOfStops = numberOfStops;
        this.seatClass = seatClass;
        this.baggageRegulations = baggageRegulations;
        this.ancillaryServices = ancillaryServices;
    }

    public void setDepartureAirportId(String departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public void setArrivalAirportId(String arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setNumberOfStops(Integer numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public void setBaggageRegulations(String baggageRegulations) {
        this.baggageRegulations = baggageRegulations;
    }

    public void setAncillaryServices(String ancillaryServices) {
        this.ancillaryServices = ancillaryServices;
    }

    public Integer getId() {
        return this.id;
    }

    public String getDepartureAirportId() {
        return this.departureAirportId;
    }

    public String getArrivalAirportId() {
        return this.arrivalAirportId;
    }

    public String getAircraftType() {
        return this.aircraftType;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getSeatClass() {
        return this.seatClass;
    }

    public String getBaggageRegulations() {
        return this.baggageRegulations;
    }

    public String getAncillaryServices() {
        return this.ancillaryServices;
    }

    public Integer getTicketPrice() {
        return this.ticketPrice;
    }

    public Integer getNumberOfStops() {
        return this.numberOfStops;
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }

    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    public LocalTime getArrivalTime() {
        return this.arrivalTime;
    }
}
