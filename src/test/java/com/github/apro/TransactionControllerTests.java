package com.github.apro;

import com.github.apro.transactions.TransactService;
import com.github.apro.transactions.Transaction;
import com.github.apro.transactions.TransactionController;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

/**
 * Created by Apro.
 */

@RunWith(SpringRunner.class)
public class TransactionControllerTests {

    @MockBean
    private TransactService service;

    @Test
    public void whenCreateTransaction_then201() {
        given()
                .standaloneSetup(new TransactionController(service))
                .contentType(ContentType.JSON)
                .body(new Transaction(12.3, Instant.now().toEpochMilli())).
                when()
                .post("/transactions").
                then()
                .statusCode(201);
    }
}
