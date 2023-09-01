package com.example.pointcalculator.point.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "calculator")
public class CalculatorConfigProperties {
    private BigDecimal pointsAbove50;
    private BigDecimal pointsAbove100;
}
