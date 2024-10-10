package eiu.cse456.flightbookingapi.controller;

import eiu.cse456.flightbookingapi.helpers.AuthHelpers;
import eiu.cse456.flightbookingapi.helpers.MyResponse;
import eiu.cse456.flightbookingapi.model.Booking;
import eiu.cse456.flightbookingapi.model.Passenger;
import eiu.cse456.flightbookingapi.respository.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import eiu.cse456.flightbookingapi.respository.PassengerRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/passenger")
@CrossOrigin(origins = "http://localhost:5173")
public class PassengerController {
    public static Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    PassengerRepository passengerService;
    @Autowired
    BookingRepository bookingService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> register(@RequestParam MultiValueMap<String, String> formData) {
        String password = formData.getFirst("password").toString();
        MyResponse responsePass = AuthHelpers.validatePassword(password);
        if (responsePass.getError() == true) {
            return new ResponseEntity<MyResponse>(responsePass, HttpStatus.CONFLICT);
        } else {
            String fullName = formData.getFirst("fullName").toString();
            String dateOfBirth = formData.getFirst("dateOfBirth").toString();
            String gender = formData.getFirst("gender").toString();
            String countryOfResidence = formData.getFirst("nationality").toString();
            String emailAddress = formData.getFirst("email").toString();
            String passwordIn = AuthHelpers.makeHashPassword(formData.getFirst("password").toString());
            Passenger passenger = new Passenger(fullName, dateOfBirth, gender, null, null, null, null, emailAddress, countryOfResidence, passwordIn);
            try {
                if (passengerService.findByEmail(passenger.getEmailAddress()) != null) {
                    return new ResponseEntity<MyResponse>(new MyResponse(true, "Account already exists"), HttpStatus.CONFLICT);
                } else {
                    passengerService.save(passenger);
                    return new ResponseEntity<MyResponse>(new MyResponse(false, "Success register"), HttpStatus.OK);
                }
            } catch (Exception err) {
                return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
            }
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> login(@RequestParam MultiValueMap<String, String> formData) {
        String email = formData.getFirst("email").toString();
        Passenger passenger;
        try {
            passenger = passengerService.findByEmail(email);
        } catch (Exception error) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, error.getMessage()), HttpStatus.BAD_GATEWAY);
        }
        if (AuthHelpers.comparePasswords(formData.getFirst("password").toString(), passenger.getPassword()))
            return new ResponseEntity<MyResponse>(new MyResponse(false, "Login Success"), HttpStatus.OK);
        else
            return new ResponseEntity<MyResponse>(new MyResponse(true, "Wrong password enter"), HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> passengerDetail(@RequestParam MultiValueMap<String, String> formData) {
        String email = formData.getFirst("email").toString();
        Passenger passenger;
        try {
            passenger = passengerService.findByEmail(email);
            return new ResponseEntity<MyResponse>(new MyResponse(false, null, passenger), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> updatePassenger(@RequestParam MultiValueMap<String, String> formData) {
        try {
            String passportNumber = formData.getFirst("passportNumber").toString();
            System.out.println(passportNumber);
            String phoneNumber = formData.getFirst("phoneNumber").toString();
            System.out.println(phoneNumber);
            String passengerEmail = formData.getFirst("emailAddress").toString();
            System.out.println(passengerEmail);

            Passenger updatePassenger = passengerService.findByEmail(passengerEmail);

            updatePassenger.setPassportNumber(passportNumber);
            updatePassenger.setPhoneNumber(phoneNumber);

            passengerService.save(updatePassenger);

            return new ResponseEntity<MyResponse>(new MyResponse(false, "Success full update", updatePassenger), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.valueOf(err.getMessage()));
        }
    }

    @RequestMapping(value = "/bookingHistory", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> getBookingHistory(@RequestParam MultiValueMap<String, String> formData) {
        String passengerEmail = formData.getFirst("passengerEmail").toString();
        try {
            ArrayList<Booking> listBooking = bookingService.getByRule(passengerEmail);
            return new ResponseEntity<MyResponse>(new MyResponse(false, "History Booking", listBooking), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }
}
