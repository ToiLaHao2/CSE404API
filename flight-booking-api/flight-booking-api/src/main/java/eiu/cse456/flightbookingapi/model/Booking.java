package eiu.cse456.flightbookingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "booking")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @PrimaryKeyJoinColumn
    @Column(name = "flight_id")
    private String flightId;
    @PrimaryKeyJoinColumn
    @Column(name = "passenger_email")
    private String passengerEmail;
    @Column(name = "number_of_passenger")
    private Integer numberOfPassengers;
    @Column(name = "booking_status")
    private String bookingStatus;
    @Column(name = "total_tickets_price")
    private Integer totalTicketsPrice;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_details")
    private String paymentDetails;
    @Column(name = "notes")
    private String notes;

    public Booking() {

    }

    public Booking(Integer id,String flightId, String passengerEmail, Integer numberOfPassengers, String bookingStatus, Integer totalTicketsPrice, String paymentMethod, String paymentDetails, String notes) {
        this.id = id;
        this.flightId = flightId;
        this.passengerEmail = passengerEmail;
        this.numberOfPassengers = numberOfPassengers;
        this.bookingStatus = bookingStatus;
        this.totalTicketsPrice = totalTicketsPrice;
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.notes = notes;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void set_NumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setTotalTicketsPrice(Integer totalTicketsPrice) {
        this.totalTicketsPrice = totalTicketsPrice;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFlightId() {
        return this.flightId;
    }

    public Integer getNumberOfPassengers() {
        return this.numberOfPassengers;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public Integer getTotalTicketsPrice() {
        return this.totalTicketsPrice;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public String getPaymentDetails() {
        return this.paymentDetails;
    }

    public String getNotes() {
        return this.notes;
    }

    public String getPassengerEmail() {
        return this.passengerEmail;
    }
}
