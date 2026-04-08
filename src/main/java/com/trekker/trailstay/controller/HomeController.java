package com.trekker.trailstay.controller;

import com.trekker.trailstay.model.BookingConfirmation;
import com.trekker.trailstay.model.BookingForm;
import com.trekker.trailstay.service.TravelCatalogService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Controller
public class HomeController {

    private final TravelCatalogService travelCatalogService;

    public HomeController(TravelCatalogService travelCatalogService) {
        this.travelCatalogService = travelCatalogService;
    }

    @GetMapping("/")
    public String home(Model model) {
        populateHome(model);
        model.addAttribute("bookingForm", new BookingForm());
        return "index";
    }

    @PostMapping("/booking")
    public String book(@Valid @ModelAttribute("bookingForm") BookingForm bookingForm,
                       BindingResult bindingResult,
                       Model model) {
        if (bookingForm.getCheckIn() != null
                && bookingForm.getCheckOut() != null
                && !bookingForm.getCheckOut().isAfter(bookingForm.getCheckIn())) {
            bindingResult.rejectValue("checkOut", "checkOut.invalid", "Check-out must be after check-in.");
        }

        if (bindingResult.hasErrors()) {
            populateHome(model);
            return "index";
        }

        long nights = ChronoUnit.DAYS.between(bookingForm.getCheckIn(), bookingForm.getCheckOut());
        BookingConfirmation confirmation = new BookingConfirmation(
                "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                bookingForm.getGuestName(),
                bookingForm.getDestinationName(),
                bookingForm.getLodgeName(),
                bookingForm.getCheckIn(),
                bookingForm.getCheckOut(),
                bookingForm.getGuests(),
                nights,
                bookingForm.getSpecialRequest()
        );

        model.addAttribute("confirmation", confirmation);
        return "confirmation";
    }

    private void populateHome(Model model) {
        model.addAttribute("destinations", travelCatalogService.getDestinations());
        model.addAttribute("lodges", travelCatalogService.getLodges());
    }
}
