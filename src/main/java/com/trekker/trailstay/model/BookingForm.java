package com.trekker.trailstay.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BookingForm {

    @NotBlank(message = "Please enter your name.")
    private String guestName;

    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Please enter your email.")
    private String email;

    @NotBlank(message = "Please choose a stay.")
    private String lodgeName;

    @NotBlank(message = "Please choose a destination.")
    private String destinationName;

    @NotNull(message = "Please choose a check-in date.")
    @FutureOrPresent(message = "Check-in must be today or later.")
    private LocalDate checkIn;

    @NotNull(message = "Please choose a check-out date.")
    @Future(message = "Check-out must be after today.")
    private LocalDate checkOut;

    @Min(value = 1, message = "At least one guest is required.")
    private int guests = 1;

    private String specialRequest;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLodgeName() {
        return lodgeName;
    }

    public void setLodgeName(String lodgeName) {
        this.lodgeName = lodgeName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }
}
