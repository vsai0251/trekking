package com.trekker.trailstay.model;

import java.time.LocalDate;

public record BookingConfirmation(
        String bookingId,
        String guestName,
        String destinationName,
        String lodgeName,
        LocalDate checkIn,
        LocalDate checkOut,
        int guests,
        long nights,
        String specialRequest
) {
}
