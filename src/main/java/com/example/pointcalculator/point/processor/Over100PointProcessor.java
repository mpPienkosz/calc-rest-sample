package com.example.pointcalculator.point.processor;

import com.example.pointcalculator.point.model.CalculatorConfigProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class Over100PointProcessor extends PointProcessor {
    public Over100PointProcessor(CalculatorConfigProperties properties) {
        super(BigDecimal.valueOf(100), properties.getPointsAbove100());
    }
}
