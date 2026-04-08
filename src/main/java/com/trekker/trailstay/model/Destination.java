package com.trekker.trailstay.model;

import java.util.List;

public record Destination(
        String name,
        String region,
        String trekDuration,
        String highlight,
        String description,
        String imagePath,
        List<String> experiences,
        List<String> foods
) {
}
