package com.example.pointcalculator.point.model;

import java.util.UUID;

public record UserPointsResult(
        UUID userId,
        Long points
) {
}
