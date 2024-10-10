package eiu.cse456.flightbookingapi.controller;

import eiu.cse456.flightbookingapi.helpers.MyResponse;
import eiu.cse456.flightbookingapi.model.Airport;
import eiu.cse456.flightbookingapi.respository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
@CrossOrigin(origins = "http://localhost:5173")
public class AirportController {

    @Autowired
    AirportRepository airportService;

    @RequestMapping(value = "/getAirport", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> getAllAirport() {
        List<Airport> listAirport = airportService.findAll();
        if (listAirport.isEmpty()) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, "No airport in database"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MyResponse>(new MyResponse(false, "Success get airport datas", listAirport), HttpStatus.OK);
    }

    @RequestMapping(value = "/addAirport", method = RequestMethod.POST)
    public ResponseEntity<MyResponse> addAirport(@RequestParam MultiValueMap<String, String> formData) {
        String airportId = formData.getFirst("airportId").toString();
        String airportName = formData.getFirst("airportName").toString();
        Airport addAirport = new Airport(airportId, airportName);

        try {
            airportService.save(addAirport);
            return new ResponseEntity<MyResponse>(new MyResponse(false, "Add new airport success"), HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<MyResponse>(new MyResponse(true, err.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }
}
