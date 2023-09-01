package com.example.pointcalculator.point.rest;

import com.example.pointcalculator.point.PointService;
import com.example.pointcalculator.point.rest.dto.UserPointsByDateResponse;
import com.example.pointcalculator.point.rest.dto.UserPointsResponse;
import com.example.pointcalculator.point.rest.mapper.RestPointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class PointController {

    private final PointService pointService;
    private final RestPointMapper mapper;

    @GetMapping("/{userId}")
    UserPointsResponse getPointsForUser(@PathVariable UUID userId) {
        return mapper.toUserPointsResponse(pointService.getPointsForUser(userId));
    }

    @GetMapping(value = "/{userId}", params = {"dateFrom", "dateTo"})
    UserPointsByDateResponse getUserPointsForPeriod(
            @PathVariable UUID userId,
            @RequestParam LocalDate dateFrom,
            @RequestParam LocalDate dateTo
    ) {
        var userPointsByDate = mapper.toUserPointsByDate(userId, dateFrom, dateTo);
        var result = pointService.getUserPointsByDate(userPointsByDate);
        return mapper.toUserPointsByDateResponse(result);
    }
}
