package com.example.pointcalculator.point.processor;

import com.example.pointcalculator.point.model.CalculatorConfigProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Over50PointProcessor extends PointProcessor {
    public Over50PointProcessor(CalculatorConfigProperties properties) {
        super(BigDecimal.valueOf(50), properties.getPointsAbove50());
    }
}
