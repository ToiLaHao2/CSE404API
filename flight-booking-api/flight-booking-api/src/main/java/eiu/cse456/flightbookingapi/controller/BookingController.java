package eiu.cse456.flightbookingapi.controller;

import eiu.cse456.flightbookingapi.helpers.MyResponse;
import eiu.cse456.flightbookingapi.model.Booking;
import eiu.cse456.flightbookingapi.model.Flight;
import eiu.cse456.flightbookingapi.model.Passenger;
import eiu.cse456.flightbookingapi.respository.BookingRepository;
import eiu.cse456.flightbookingapi.respository.FlightRepository;
import eiu.cse456.flightbookingapi.respository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    BookingRepository bookingService;
    @Autowired
    FlightRepository flightService;
    @Autowired
    PassengerRepository passengerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> addNewBooking(@RequestParam MultiValueMap<String, String> formData) {
        try {
            String flightId = formData.getFirst("flightId").toString();
            System.out.println(flightId);
            String passengerEmail = formData.getFirst("passengerEmail").toString();
            System.out.println(passengerEmail);
            String numberOfPassenger = formData.getFirst("numberOfPassenger").toString();
            System.out.println(numberOfPassenger);
            String totalTicketsPrice = formData.getFirst("totalTicketsPrice").toString();
            System.out.println(totalTicketsPrice);
            String paymentMethod = formData.getFirst("paymentMethod").toString();
            System.out.println(paymentMethod);
            String paymentDetails = formData.getFirst("paymentDetails").toString();
            System.out.println(paymentDetails);
            String notes = formData.getFirst("notes").toString();
            System.out.println(notes);
            Booking addBooking = new Booking();
            if (flightService.findById(Integer.parseInt(flightId)) != null && passengerService.findByEmail(passengerEmail) != null) {
                addBooking.setFlightId(flightId);
                addBooking.setPassengerEmail(passengerEmail);
                addBooking.setNumberOfPassengers(Integer.parseInt(numberOfPassenger));
                addBooking.setBookingStatus(BookingStatus.ON_BOOKING.toString());
                addBooking.setTotalTicketsPrice(Integer.parseInt(totalTicketsPrice));
                addBooking.setPaymentMethod(paymentMethod);
                addBooking.setPaymentDetails(paymentDetails);
                addBooking.setNotes(notes);

                bookingService.save(addBooking);
            }

            return new ResponseEntity<MyResponse>(new MyResponse(false, "Booking successfull", addBooking), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/getBooking", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> getBooking(@RequestParam MultiValueMap<String, String> formData) {
        try {
            String id = formData.getFirst("id").toString();
            Booking booking = bookingService.findById(Integer.parseInt(id));
            return new ResponseEntity<MyResponse>(new MyResponse(false, "Here your booking", booking), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> updateBooking(@RequestParam MultiValueMap<String, String> formData) {
        String id = formData.getFirst("id").toString();
        try {
            Booking presentBooking = bookingService.findById(Integer.parseInt(id));
            String flightId = formData.get("flightId").toString();
            String numberOfPassengers = formData.get("numberOfPassengers").toString();
            String totalTicketsPrice = formData.get("totalTicketsPrice").toString();
            String paymentMethod = formData.get("paymentMethod").toString();
            String paymentDetails = formData.get("paymentDetails").toString();
            String notes = formData.get("notes").toString();

            presentBooking.setFlightId(flightId);
            presentBooking.setNumberOfPassengers(Integer.parseInt(numberOfPassengers));
            presentBooking.setTotalTicketsPrice(Integer.parseInt(totalTicketsPrice));
            presentBooking.setPaymentMethod(paymentMethod);
            presentBooking.setPaymentDetails(paymentDetails);
            presentBooking.setNotes(notes);

            bookingService.save(presentBooking);

            return new ResponseEntity<MyResponse>(new MyResponse(false, "Update successfull", presentBooking), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/deleteBooking", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> deleteBooking(@RequestParam MultiValueMap<String, String> formData) {
        String id = formData.getFirst("id").toString();
        String passengerEmail = formData.getFirst("passengerEmail").toString();
        try {
            Booking presentBooking = bookingService.findById(Integer.parseInt(id));
            if (presentBooking != null && passengerEmail.equals(presentBooking.getPassengerEmail())) {
                bookingService.delete(presentBooking);
                return new ResponseEntity<MyResponse>(new MyResponse(false, "Delete successfull"), HttpStatus.OK);
            }
            return new ResponseEntity<MyResponse>(new MyResponse(true, "Wrong input"), HttpStatus.NOT_FOUND);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }
}
