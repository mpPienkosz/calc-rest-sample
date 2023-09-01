package com.example.pointcalculator.point.model;

import java.time.LocalDate;
import java.util.UUID;

public record UserPointsByDateResult(

        UUID userId,

        Long points,
        LocalDate dateFrom,
        LocalDate dateTo
) {
}
