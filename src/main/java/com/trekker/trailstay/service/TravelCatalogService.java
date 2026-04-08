package com.trekker.trailstay.service;

import com.trekker.trailstay.model.Destination;
import com.trekker.trailstay.model.Lodge;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TravelCatalogService {

    public List<Destination> getDestinations() {
        return List.of(
                new Destination(
                        "Valley of Pines",
                        "Himachal Highlands",
                        "2 days / moderate",
                        "Forest ridges, cool streams, sunrise camps",
                        "A scenic mountain route with cedar forests, soft morning light, and calm village stays after the trek.",
                        "/images/valley-of-pines.svg",
                        List.of("Guided ridge walks", "Bonfire storytelling", "Sunrise photography"),
                        List.of("Siddu with ghee", "Smoky rajma chawal", "Apricot tea")
                ),
                new Destination(
                        "Cloud Peak Loop",
                        "Western Ghats",
                        "1 day / beginner friendly",
                        "Mist trails, waterfalls, and valley viewpoints",
                        "Ideal for first-time trekkers who want lush green slopes, short climbs, and lively local food stalls at the base camp.",
                        "/images/cloud-peak-loop.svg",
                        List.of("Waterfall stopovers", "Monsoon trail walks", "Village snack breaks"),
                        List.of("Banana fritters", "Spiced chai", "Jackfruit chips")
                ),
                new Destination(
                        "Sunset Ridge Trail",
                        "Nilgiri Escapes",
                        "3 days / adventurous",
                        "Open meadows, tea country, and starry camps",
                        "A longer route that mixes rolling grasslands with tea estate views, warm food, and cozy mountain lodging.",
                        "/images/sunset-ridge.svg",
                        List.of("Stargazing deck", "Tea estate visits", "Campfire acoustic evenings"),
                        List.of("Millet dosa", "Pepper chicken curry", "Cardamom pudding")
                )
        );
    }

    public List<Lodge> getLodges() {
        return List.of(
                new Lodge(
                        "Pine Nest Rooms",
                        "Near Valley of Pines basecamp",
                        "/images/stay-pine.svg",
                        "Warm wooden rooms with mountain-facing balconies and early breakfast for trekkers.",
                        new BigDecimal("4200"),
                        List.of("Mountain view", "Breakfast included", "Campfire courtyard")
                ),
                new Lodge(
                        "Misty Trail Lodge",
                        "Cloud Peak village center",
                        "/images/stay-misty.svg",
                        "Comfortable family rooms close to the trek entrance and local food street.",
                        new BigDecimal("3600"),
                        List.of("Local food market nearby", "Hot showers", "Guide desk")
                ),
                new Lodge(
                        "Summit Hearth Retreat",
                        "Sunset Ridge upper valley",
                        "/images/stay-summit.svg",
                        "Premium stay with shared lounge, tea tastings, and evening music sessions.",
                        new BigDecimal("5100"),
                        List.of("Tea tasting", "Bonfire lounge", "Pickup on request")
                )
        );
    }
}
