package com.example.pointcalculator.transaction.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;


public record RegisterTransactionRequest(BigDecimal amount, UUID userId) {
}
