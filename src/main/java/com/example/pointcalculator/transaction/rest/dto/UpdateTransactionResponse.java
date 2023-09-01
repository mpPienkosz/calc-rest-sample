package com.example.pointcalculator.transaction.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateTransactionResponse(
        UUID transactionId,
        UUID userId,
        BigDecimal amount
) {
}
