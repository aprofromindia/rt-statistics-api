package com.github.apro.transactions;

import com.github.apro.config.AppConstants;
import com.github.apro.error.Error;
import com.github.apro.error.TransactionCreationException;
import com.github.apro.statistics.StatisticsController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactService service;

    @PostMapping(value = AppConstants.TRANSACTIONS_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<?>> createTransaction(@RequestBody @Valid Transaction transaction,
                                                     BindingResult result)
            throws TransactionCreationException {
        if (!result.hasErrors()) {
            service.save(transaction);
            Resource<String> resource = new Resource<>("Transaction logged",
                    linkTo(methodOn(StatisticsController.class)
                            .getStats())
                            .withRel(AppConstants.REL_STATISTICS));
            return new ResponseEntity<>(resource, HttpStatus.CREATED);
        } else {
            throw new TransactionCreationException(Error.REQ_BODY_ERROR,
                    "Transaction creation error");
        }
    }
}
