package com.example.pointcalculator.point.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PointControllerTest {

    private static final String POINTS_PATH = "/points";

    @Autowired
    private WebTestClient webClient;


    @Test
    @Sql(scripts = {"classpath:data/cleanup.sql", "classpath:data/sample_transaction.sql"})
        //one transaction 100.55 for user '8f6a628d-087c-4c9f-ae51-77fb1e744099'
    void shouldReturnAllPointsForUser() {
        webClient.get()
                .uri("/points/{userId}", "8f6a628d-087c-4c9f-ae51-77fb1e744099")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userId").isEqualTo("8f6a628d-087c-4c9f-ae51-77fb1e744099")
                .jsonPath("$.points").isEqualTo(51);
    }

    @Test
    @Sql(scripts = {"classpath:data/cleanup.sql", "classpath:data/last_month_test_data.sql"})
        //2 transactions for 58
    void shouldCountPointsForLastMonth() {
        var dateFrom = LocalDate.parse("2023-05-01");
        var dateTo = LocalDate.parse("2023-06-01");
        var userId = "8f6a628d-087c-4c9f-ae51-77fb1e744099";

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/points/{userId}")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build(userId)
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userId").isEqualTo(userId)
                .jsonPath("$.points").isEqualTo(16)
                .jsonPath("$.dateFrom").isEqualTo(dateFrom.toString())
                .jsonPath("$.dateTo").isEqualTo(dateTo.toString());
    }

    @Test
    @Sql(scripts = {"classpath:data/cleanup.sql", "classpath:data/last_month_test_data.sql"})
        //2 transactions 156 + 57
    void shouldCalculatePointsFor04Month() {
        var dateFrom = LocalDate.parse("2023-04-01");
        var dateTo = LocalDate.parse("2023-05-01");
        var userId = "8f6a628d-087c-4c9f-ae51-77fb1e744099";

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/points/{userId}")
                        .queryParam("dateFrom", dateFrom)
                        .queryParam("dateTo", dateTo)
                        .build(userId)
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.userId").isEqualTo(userId)
                .jsonPath("$.points").isEqualTo(225)
                .jsonPath("$.dateFrom").isEqualTo(dateFrom.toString())
                .jsonPath("$.dateTo").isEqualTo(dateTo.toString());
    }
}
