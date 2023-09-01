package com.example.pointcalculator.point.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserPointsByDateResponse(
        UUID userId,
        Long points,
        LocalDate dateFrom,
        LocalDate dateTo
) {

}
