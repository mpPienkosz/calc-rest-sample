package com.example.pointcalculator.point.processor;

import com.example.pointcalculator.common.Constants;
import com.example.pointcalculator.point.model.CalculatorConfigProperties;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.example.pointcalculator.common.Constants.NEGATIVE_VALUE_RESULT;

@RequiredArgsConstructor
public abstract class PointProcessor {
    protected final BigDecimal countAboveAmount;
    protected final BigDecimal pointMultiplier;

    public Long process(BigDecimal txAmount) {
        var result = txAmount.round(MathContext.DECIMAL128)
                .subtract(countAboveAmount)
                .multiply(pointMultiplier);

        return result.signum() == NEGATIVE_VALUE_RESULT ? Constants.INITIAL_POINTS : result.longValue();
    }
}
