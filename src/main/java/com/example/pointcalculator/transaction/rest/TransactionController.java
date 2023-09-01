package com.example.pointcalculator.transaction.rest;

import com.example.pointcalculator.transaction.TransactionService;
import com.example.pointcalculator.transaction.rest.dto.RegisterTransactionRequest;
import com.example.pointcalculator.transaction.rest.dto.RegisterTransactionResponse;
import com.example.pointcalculator.transaction.rest.dto.UpdateTransactionRequest;
import com.example.pointcalculator.transaction.rest.dto.UpdateTransactionResponse;
import com.example.pointcalculator.transaction.rest.mapper.RestTransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
class TransactionController {
    private final TransactionService transactionService;
    private final RestTransactionMapper transactionMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RegisterTransactionResponse registerTransaction(@RequestBody RegisterTransactionRequest request) {
        var transaction = transactionMapper.toRegisterTransaction(request);
        var result = transactionService.registerTransaction(transaction);
        return transactionMapper.toRegisterTransactionResponse(result);
    }

    @PatchMapping("/{transactionId}")
    UpdateTransactionResponse updateTransaction(
            @PathVariable UUID transactionId,
            @RequestBody UpdateTransactionRequest request
    ) {
        var updateTransactionDto = transactionMapper.toUpdateTransaction(transactionId, request);
        var result = transactionService.updateTransaction(updateTransactionDto);
        return transactionMapper.toUpdateTransactionResponse(result);
    }
}
