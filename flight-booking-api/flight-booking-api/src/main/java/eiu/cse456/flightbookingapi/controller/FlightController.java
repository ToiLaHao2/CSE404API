package eiu.cse456.flightbookingapi.controller;

import eiu.cse456.flightbookingapi.helpers.MyResponse;
import eiu.cse456.flightbookingapi.model.Flight;
import eiu.cse456.flightbookingapi.respository.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import eiu.cse456.flightbookingapi.respository.FlightRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {
    public static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    FlightRepository flightService;
    @Autowired
    AirportRepository airportService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public ResponseEntity<MyResponse> listAllFlight() {
        List<Flight> listFlights = flightService.findAll();
        if (listFlights.isEmpty()) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, "No Flights at this time"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<MyResponse>(new MyResponse(false, "Get Flights successfull", listFlights), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFlight", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> getFlight(@RequestParam MultiValueMap<String, String> formData) {
        String flightId = formData.getFirst("id").toString();
        try {
            Flight flightPresent = flightService.findById(Integer.parseInt(flightId));
            return new ResponseEntity<MyResponse>(new MyResponse(false, "Success get flight detail", flightPresent), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getOneWayFlights", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> listOneWayFlight(@RequestParam MultiValueMap<String, String> formData) {
        List<Flight> listOneWayFlight = new ArrayList<>();

        try {
            String departureAirportId = formData.getFirst("departureAirportId").toString();
            System.out.println(departureAirportId);
            String arrivalAirportId = formData.getFirst("arrivalAirportId").toString();
            System.out.println(arrivalAirportId);
            String departureDateString = formData.getFirst("departureDate").toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date departureDate = null;
            try {
                // Chuyển đổi chuỗi thành java.util.Date
                java.util.Date utilDate = sdf.parse(departureDateString);

                // Chuyển đổi java.util.Date thành java.sql.Date
                departureDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (airportService.findById(departureAirportId) != null && airportService.findById(arrivalAirportId) != null) {
                try {
                    listOneWayFlight = flightService.findByRule(departureAirportId, arrivalAirportId, departureDate);
                    return new ResponseEntity<MyResponse>(new MyResponse(false, null, listOneWayFlight), HttpStatus.OK);
                } catch (Exception err) {
                    System.out.println(err.getMessage());
                    return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.NOT_FOUND);
                }
            } else {
                System.out.println("Wrong Airport Id");
                return new ResponseEntity<MyResponse>(new MyResponse(true, "Wrong Airport Id"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception err) {
            System.out.println(err.getMessage());
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


//    @RequestMapping(value = "/getRoundTripFlights", method = RequestMethod.POST)
//    public
}
