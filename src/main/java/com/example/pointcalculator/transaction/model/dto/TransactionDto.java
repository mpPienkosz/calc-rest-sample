package com.example.pointcalculator.transaction.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto(BigDecimal amount, UUID userId) {
}
