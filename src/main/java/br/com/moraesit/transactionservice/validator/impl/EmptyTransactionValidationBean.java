package br.com.moraesit.transactionservice.validator.impl;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.exception.DomainBusinessException;
import br.com.moraesit.transactionservice.validator.TransactionValidation;

public class EmptyTransactionValidationBean implements TransactionValidation {
    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {

    }
}
