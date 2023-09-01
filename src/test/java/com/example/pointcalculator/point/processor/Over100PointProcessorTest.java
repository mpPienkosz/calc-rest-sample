package com.example.pointcalculator.point.processor;

import com.example.pointcalculator.point.model.CalculatorConfigProperties;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Over100PointProcessorTest {

    private final CalculatorConfigProperties properties = new CalculatorConfigProperties(BigDecimal.ONE, BigDecimal.valueOf(2L));
    private final Over100PointProcessor over100PointProcessor = new Over100PointProcessor(properties);


    @Test
    void shouldCalculatePointsForPositiveAmount() {
        BigDecimal txAmount = BigDecimal.valueOf(120);

        var result = over100PointProcessor.process(txAmount);

        assertEquals(40, result);
    }

    @Test
    void shouldReturn0ForTransactionBelow100() {
        BigDecimal txAmount = BigDecimal.valueOf(90);

        var result = over100PointProcessor.process(txAmount);

        assertEquals(0, result);
    }

    //silly case as transaction amount won't be a negative value
    @Test
    void shouldReturn0ForNegativeAmount() {
        BigDecimal txAmount = BigDecimal.valueOf(-90);

        var result = over100PointProcessor.process(txAmount);

        assertEquals(0, result);
    }
}