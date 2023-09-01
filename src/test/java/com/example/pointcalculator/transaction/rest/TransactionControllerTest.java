package com.example.pointcalculator.transaction.rest;

import com.example.pointcalculator.transaction.rest.dto.RegisterTransactionRequest;
import com.example.pointcalculator.transaction.rest.dto.UpdateTransactionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class TransactionControllerTest {

    private static final String TX_PATH = "/transactions";

    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldSuccessfullyRegisterUserTransaction() {
        var transactionRequest = new RegisterTransactionRequest(
                BigDecimal.valueOf(200), UUID.randomUUID()
        );

        webClient.post()
                .uri(TX_PATH)
                .body(BodyInserters.fromValue(transactionRequest))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.transactionId").isNotEmpty();

    }

    @Test
    void shouldFailOnNonExistingVerb() {
        webClient.delete()
                .uri(TX_PATH)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    @Sql(scripts = {"classpath:data/cleanup.sql", "classpath:data/sample_transaction.sql"})
    void shouldSuccessfullyUpdateUserForTransaction() {
        var txToUpdate = UUID.fromString("74059a1b-3143-4aec-8c0c-186c25a74da4");
        var userId = UUID.fromString("8f6a628d-087c-4c9f-ae51-77fb1e744099");
        var updateTxRequest = new UpdateTransactionRequest(userId, null);

        webClient.patch()
                .uri("/transactions/{transactionID}", txToUpdate)
                .body(BodyInserters.fromValue(updateTxRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.transactionId").isEqualTo(txToUpdate.toString())
                .jsonPath("$.userId").isEqualTo(userId.toString())
                .jsonPath("$.amount").isEqualTo("100.55");
    }

    @Test
    @Sql(scripts = {"classpath:data/cleanup.sql", "classpath:data/sample_transaction.sql"})
    void shouldUpdateTxUserAndAmount() {
        var txToUpdate = UUID.fromString("74059a1b-3143-4aec-8c0c-186c25a74da4");
        var userId = UUID.fromString("8f6a628d-087c-4c9f-ae51-77fb1e744099");
        var tx = new UpdateTransactionRequest(userId, BigDecimal.ONE);

        webClient.patch()
                .uri("/transactions/{transactionID}", txToUpdate)
                .body(BodyInserters.fromValue(tx))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.transactionId").isEqualTo(txToUpdate.toString())
                .jsonPath("$.userId").isEqualTo(userId.toString())
                .jsonPath("$.amount").isEqualTo(tx.txAmount());
    }

    @Test
    void shouldReturnNotFoundOnNonExistingTransaction() {
        var txToUpdate = UUID.fromString("8f6a628d-087c-4c9f-ae51-77fb1e744099");
        var userId = UUID.fromString("8f6a628d-087c-4c9f-ae51-77fb1e744099");
        var tx = new UpdateTransactionRequest(userId, BigDecimal.ONE);

        webClient.patch()
                .uri("/transactions/{transactionID}", txToUpdate)
                .body(BodyInserters.fromValue(tx))
                .exchange()
                .expectStatus().isNotFound();
    }
}
