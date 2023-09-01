package com.example.pointcalculator.point.rest.mapper;

import com.example.pointcalculator.point.model.UserPointsByDate;
import com.example.pointcalculator.point.model.UserPointsByDateResult;
import com.example.pointcalculator.point.model.UserPointsResult;
import com.example.pointcalculator.point.rest.dto.UserPointsByDateResponse;
import com.example.pointcalculator.point.rest.dto.UserPointsResponse;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RestPointMapper {

    UserPointsByDate toUserPointsByDate(UUID userId, LocalDate dateFrom, LocalDate dateTo);

    UserPointsByDateResponse toUserPointsByDateResponse(UserPointsByDateResult result);

    UserPointsResponse toUserPointsResponse(UserPointsResult result);
}
