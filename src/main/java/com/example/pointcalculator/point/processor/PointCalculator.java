package com.example.pointcalculator.point.processor;

import com.example.pointcalculator.common.Constants;
import com.example.pointcalculator.point.model.CalculateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
public class PointCalculator {
    private final List<PointProcessor> pointProcessors;

    public CalculateResult calculate(BigDecimal txAmount) {
        var totalPoints = pointProcessors.parallelStream()
                .map(pointProcessor -> pointProcessor.process(txAmount))
                .filter(this::greaterThan0)
                .reduce(Constants.INITIAL_POINTS, Long::sum);

        return new CalculateResult(totalPoints);
    }

    private Boolean greaterThan0(Long processResult) {
        return processResult > 0;
    }
}
