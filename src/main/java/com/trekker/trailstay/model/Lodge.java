package com.trekker.trailstay.model;

import java.math.BigDecimal;
import java.util.List;

public record Lodge(
        String name,
        String location,
        String imagePath,
        String description,
        BigDecimal pricePerNight,
        List<String> amenities
) {
}
