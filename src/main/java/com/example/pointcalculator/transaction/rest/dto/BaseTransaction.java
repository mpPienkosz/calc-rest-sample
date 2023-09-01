package com.example.pointcalculator.transaction.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BaseTransaction(
        UUID transactionId,
        BigDecimal amount
) {

}
