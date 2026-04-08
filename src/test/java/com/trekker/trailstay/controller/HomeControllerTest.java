package com.trekker.trailstay.controller;

import com.trekker.trailstay.service.TravelCatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelCatalogService travelCatalogService;

    @Test
    void homePageLoadsWithCatalogData() throws Exception {
        given(travelCatalogService.getDestinations()).willReturn(TestData.destinations());
        given(travelCatalogService.getLodges()).willReturn(TestData.lodges());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("destinations"))
                .andExpect(model().attributeExists("lodges"))
                .andExpect(model().attributeExists("bookingForm"));
    }

    @Test
    void bookingSucceedsWithValidInput() throws Exception {
        mockMvc.perform(post("/booking")
                        .param("guestName", "Sai")
                        .param("email", "sai@example.com")
                        .param("destinationName", "Valley of Pines")
                        .param("lodgeName", "Pine Nest Rooms")
                        .param("checkIn", "2030-05-10")
                        .param("checkOut", "2030-05-12")
                        .param("guests", "2")
                        .param("specialRequest", "Need campfire dinner"))
                .andExpect(status().isOk())
                .andExpect(view().name("confirmation"))
                .andExpect(model().attributeExists("confirmation"));
    }

    @Test
    void bookingFailsWhenCheckoutIsBeforeCheckin() throws Exception {
        given(travelCatalogService.getDestinations()).willReturn(TestData.destinations());
        given(travelCatalogService.getLodges()).willReturn(TestData.lodges());

        mockMvc.perform(post("/booking")
                        .param("guestName", "Sai")
                        .param("email", "sai@example.com")
                        .param("destinationName", "Valley of Pines")
                        .param("lodgeName", "Pine Nest Rooms")
                        .param("checkIn", "2030-05-12")
                        .param("checkOut", "2030-05-10")
                        .param("guests", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors("bookingForm", "checkOut"))
                .andExpect(model().attributeExists("destinations"))
                .andExpect(model().attributeExists("lodges"));
    }

    @Test
    void bookingFailsWhenRequiredFieldsAreMissing() throws Exception {
        given(travelCatalogService.getDestinations()).willReturn(TestData.destinations());
        given(travelCatalogService.getLodges()).willReturn(TestData.lodges());

        mockMvc.perform(post("/booking")
                        .param("guestName", "")
                        .param("email", "bad-email")
                        .param("destinationName", "")
                        .param("lodgeName", "")
                        .param("guests", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors(
                        "bookingForm",
                        "guestName",
                        "email",
                        "destinationName",
                        "lodgeName",
                        "checkIn",
                        "checkOut",
                        "guests"
                ));
    }

    private static final class TestData {

        private static List<com.trekker.trailstay.model.Destination> destinations() {
            return List.of(new com.trekker.trailstay.model.Destination(
                    "Valley of Pines",
                    "Himachal Highlands",
                    "2 days / moderate",
                    "Forest ridges, cool streams, sunrise camps",
                    "A scenic mountain route.",
                    "/images/valley-of-pines.svg",
                    List.of("Guided ridge walks"),
                    List.of("Siddu with ghee")
            ));
        }

        private static List<com.trekker.trailstay.model.Lodge> lodges() {
            return List.of(new com.trekker.trailstay.model.Lodge(
                    "Pine Nest Rooms",
                    "Near Valley of Pines basecamp",
                    "/images/stay-pine.svg",
                    "Warm wooden rooms.",
                    new BigDecimal("4200"),
                    List.of("Mountain view")
            ));
        }
    }
}
