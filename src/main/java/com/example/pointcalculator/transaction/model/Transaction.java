package com.example.pointcalculator.transaction.model;

import com.example.pointcalculator.transaction.rest.dto.RegisterTransactionRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private UUID transactionId;

    @Column(scale = 2)
    private BigDecimal amount;

    private UUID userId;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate modifiedDate;

    public static Transaction of(RegisterTransactionRequest request) {
        return new Transaction(null, request.amount(), request.userId(), null, null);
    }
}
