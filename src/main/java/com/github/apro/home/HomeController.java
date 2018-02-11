package com.github.apro.home;

import com.github.apro.config.AppConstants;
import com.github.apro.error.TransactionCreationException;
import com.github.apro.statistics.StatisticsController;
import com.github.apro.transactions.Transaction;
import com.github.apro.transactions.TransactionController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apro.
 */
@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    HttpEntity<Resource<String>> getHome() throws TransactionCreationException {

        final Resource<String> resource =
                new Resource<>(
                        "Welcome to the Statistics API",
                        linkTo(
                                methodOn(TransactionController.class)
                                        .createTransaction(
                                                new Transaction(0.0, 0l),
                                                BindingResultUtils.getBindingResult(
                                                        Collections.emptyMap(), "")))
                                .withRel(AppConstants.REL_TRANSACTIONS),
                        linkTo(methodOn(StatisticsController.class).getStats())
                                .withRel(AppConstants.REL_STATISTICS));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
