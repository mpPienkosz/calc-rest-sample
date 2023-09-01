package com.example.pointcalculator.point;

import com.example.pointcalculator.point.model.CalculateResult;
import com.example.pointcalculator.point.model.UserPointsByDate;
import com.example.pointcalculator.point.model.UserPointsByDateResult;
import com.example.pointcalculator.point.model.UserPointsResult;
import com.example.pointcalculator.point.processor.PointCalculator;
import com.example.pointcalculator.transaction.TransactionService;
import com.example.pointcalculator.transaction.rest.dto.BaseTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.pointcalculator.common.Constants.INITIAL_POINTS;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointCalculator pointCalculator;
    private final TransactionService transactionService;

    public UserPointsResult getPointsForUser(UUID userId) {
        List<BaseTransaction> transactions = transactionService.findUserTransactions(userId);
        var points = calculate(transactions);
        return new UserPointsResult(userId, points);
    }

    public UserPointsByDateResult getUserPointsByDate(UserPointsByDate userPointsByDate) {
        var transactions = transactionService.findTransactions(
                userPointsByDate.userId(), userPointsByDate.dateFrom(), userPointsByDate.dateTo()
        );

        var points = calculate(transactions);

        return new UserPointsByDateResult(
                userPointsByDate.userId(), points, userPointsByDate.dateFrom(), userPointsByDate.dateTo()
        );
    }

    private Long calculate(List<BaseTransaction> transactions) {
        return transactions.stream()
                .map(tx -> pointCalculator.calculate(tx.amount()))
                .map(CalculateResult::points)
                .reduce(INITIAL_POINTS, Long::sum);
    }
}
