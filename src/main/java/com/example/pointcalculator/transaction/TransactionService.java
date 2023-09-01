package com.example.pointcalculator.transaction;

import com.example.pointcalculator.common.exception.EntityNotFoundException;
import com.example.pointcalculator.transaction.model.TransactionRepository;
import com.example.pointcalculator.transaction.model.dto.RegisterTransactionResult;
import com.example.pointcalculator.transaction.model.dto.TransactionDto;
import com.example.pointcalculator.transaction.model.dto.UpdateTransactionDto;
import com.example.pointcalculator.transaction.model.mapper.TransactionMapper;
import com.example.pointcalculator.transaction.rest.dto.BaseTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;

    public RegisterTransactionResult registerTransaction(TransactionDto transactionDto) {
        var transaction = mapper.toTransaction(transactionDto);
        return mapper.toTransactionResult(transactionRepository.save(transaction));
    }

    public UpdateTransactionDto updateTransaction(UpdateTransactionDto updateTransactionDto) {
        var transaction = transactionRepository.findById(updateTransactionDto.transactionId())
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found!"));

        if (updateTransactionDto.amount() != null) {
            transaction.setAmount(updateTransactionDto.amount());
        }

        if (updateTransactionDto.userId() != null) {
            transaction.setUserId(updateTransactionDto.userId());
        }

        return mapper.toUpdateTransaction(transactionRepository.save(transaction));
    }

    public List<BaseTransaction> findTransactions(UUID userId, LocalDate from, LocalDate to) {
        return transactionRepository.findByUserIdAndCreatedDateBetween(userId, from, to)
                .stream()
                .map(tx -> new BaseTransaction(tx.getTransactionId(), tx.getAmount()))
                .toList();
    }

    public List<BaseTransaction> findUserTransactions(UUID userId) {
        return transactionRepository.findByUserId(userId)
                .stream()
                .map(tx -> new BaseTransaction(tx.getTransactionId(), tx.getAmount()))
                .toList();
    }
}
