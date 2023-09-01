package com.example.pointcalculator.transaction.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegisterTransactionResult(
        UUID transactionId,
        BigDecimal amount,
        UUID userId,
        LocalDate createdDate,
        LocalDate modifiedDate
) {
}
