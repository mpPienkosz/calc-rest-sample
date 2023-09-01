package com.example.pointcalculator.transaction.model.mapper;

import com.example.pointcalculator.transaction.model.Transaction;
import com.example.pointcalculator.transaction.model.dto.RegisterTransactionResult;
import com.example.pointcalculator.transaction.model.dto.TransactionDto;
import com.example.pointcalculator.transaction.model.dto.UpdateTransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toTransaction(TransactionDto transaction);

    UpdateTransactionDto toUpdateTransaction(Transaction transaction);

    RegisterTransactionResult toTransactionResult(Transaction save);
}
