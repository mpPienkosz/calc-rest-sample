package com.example.pointcalculator.point;

import com.example.pointcalculator.point.model.CalculateResult;
import com.example.pointcalculator.point.model.UserPointsByDate;
import com.example.pointcalculator.point.processor.PointCalculator;
import com.example.pointcalculator.transaction.TransactionService;
import com.example.pointcalculator.transaction.rest.dto.BaseTransaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;


//to do check example calculation from description
// there could be a small mistake
// or calculation logic here needs to be changed
class PointServiceTest {

    private final PointCalculator pointCalculator = Mockito.mock(PointCalculator.class);
    private final TransactionService transactionService = Mockito.mock(TransactionService.class);
    private final PointService pointService = new PointService(pointCalculator, transactionService);

    @Test
    void shouldCalculatePointsForEachTransaction() {
        var userId = UUID.randomUUID();
        var transactions = Arrays.asList(
                new BaseTransaction(UUID.randomUUID(), BigDecimal.valueOf(120)),
                new BaseTransaction(UUID.randomUUID(), BigDecimal.valueOf(150))
        );
        when(transactionService.findUserTransactions(userId)).thenReturn(transactions);
        when(pointCalculator.calculate(BigDecimal.valueOf(120))).thenReturn(new CalculateResult(20L));
        when(pointCalculator.calculate(BigDecimal.valueOf(150))).thenReturn(new CalculateResult(30L));

        var result = pointService.getPointsForUser(userId);

        assertEquals(50, result.points());
    }

    @Test
    void shouldReturnInitial0WhenUserDoesNotHaveTransactions() {
        var userId = UUID.randomUUID();
        when(transactionService.findUserTransactions(userId)).thenReturn(List.of());

        var result = pointService.getPointsForUser(userId);

        assertEquals(0, result.points());
        verifyNoInteractions(pointCalculator);
    }

    @Test
    void shouldCalculatePointsByDate() {
        var userId = UUID.randomUUID();
        var sampleDate = LocalDate.now();
        var request = new UserPointsByDate(userId, sampleDate.minusMonths(3), sampleDate);
        when(transactionService.findTransactions(userId, sampleDate.minusMonths(3), sampleDate)).thenReturn(List.of());

        var result = pointService.getUserPointsByDate(request);

        assertEquals(0L, result.points());
        assertEquals(sampleDate, result.dateTo());
        assertEquals(sampleDate.minusMonths(3), result.dateFrom());
        verifyNoInteractions(pointCalculator);
    }
}
