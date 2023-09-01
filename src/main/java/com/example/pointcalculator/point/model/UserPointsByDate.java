package com.example.pointcalculator.point.model;

import java.time.LocalDate;
import java.util.UUID;

public record UserPointsByDate(
        UUID userId,
        LocalDate dateFrom,
        LocalDate dateTo
) {
}
