package com.example.pointcalculator.transaction.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    List<Transaction> findByUserIdAndCreatedDateBetween(UUID userId, LocalDate from, LocalDate to);

    List<Transaction> findByUserId(UUID userId);
}
