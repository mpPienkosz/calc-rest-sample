package com.example.pointcalculator.point.rest.dto;

import java.util.UUID;

public record UserPointsResponse(
        UUID userId,
        Long points
) {

}
