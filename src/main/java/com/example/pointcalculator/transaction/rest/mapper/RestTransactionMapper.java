package com.example.pointcalculator.transaction.rest.mapper;

import com.example.pointcalculator.transaction.model.dto.RegisterTransactionResult;
import com.example.pointcalculator.transaction.model.dto.TransactionDto;
import com.example.pointcalculator.transaction.model.dto.UpdateTransactionDto;
import com.example.pointcalculator.transaction.rest.dto.RegisterTransactionRequest;
import com.example.pointcalculator.transaction.rest.dto.RegisterTransactionResponse;
import com.example.pointcalculator.transaction.rest.dto.UpdateTransactionRequest;
import com.example.pointcalculator.transaction.rest.dto.UpdateTransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RestTransactionMapper {
    TransactionDto toRegisterTransaction(RegisterTransactionRequest registerTransactionRequest);

    RegisterTransactionResponse toRegisterTransactionResponse(RegisterTransactionResult result);


    @Mapping(source = "request.txAmount", target = "amount")
    UpdateTransactionDto toUpdateTransaction(UUID transactionId, UpdateTransactionRequest request);

    UpdateTransactionResponse toUpdateTransactionResponse(UpdateTransactionDto result);

}
