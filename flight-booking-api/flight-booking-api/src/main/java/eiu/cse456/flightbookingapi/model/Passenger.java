package eiu.cse456.flightbookingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "passenger")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "gender")
    private String gender;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "ticket_type")
    private String ticketType;
    @Column(name = "passport_number")
    private String passportNumber;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "country_of_residence")
    private String countryOfResidence;

    @Column(name = "password")
    private String password;

    public Passenger() {

    }

    public Passenger(String fullName, String dateOfBirth, String gender, String nationality, String ticketType, String passportNumber, String phoneNumber, String emailAddress, String countryOfResidence, String passsword) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.countryOfResidence = countryOfResidence;
        this.passportNumber = passportNumber;
        this.email = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = passsword;
        this.ticketType = ticketType;
    }

    public Passenger(Integer id, String fullName, String dateOfBirth, String gender, String nationality, String ticketType, String passportNumber, String phoneNumber, String emailAddress, String countryOfResidence, String passsword) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.countryOfResidence = countryOfResidence;
        this.passportNumber = passportNumber;
        this.email = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = passsword;
        this.ticketType = ticketType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return email;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public String getPassword() {
        return this.password;
    }
}
