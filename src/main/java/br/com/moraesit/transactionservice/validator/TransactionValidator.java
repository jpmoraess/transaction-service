package br.com.moraesit.transactionservice.validator;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.exception.DomainBusinessException;

@FunctionalInterface
public interface TransactionValidator {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBusinessException;
}
