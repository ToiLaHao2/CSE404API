package eiu.cse456.flightbookingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "eiu.cse456.flightbookingapi")
public class FlightBookingApiApplication {

	public static void main(String[] args) {
		System.out.println("Hello");
		SpringApplication.run(FlightBookingApiApplication.class, args);
	}

}
