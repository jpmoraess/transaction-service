package br.com.moraesit.transactionservice.validator.impl;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.exception.DomainBusinessException;
import br.com.moraesit.transactionservice.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConditionalOnProperty(
        value = "${transaction.validation.banco}",
        havingValue = "true",
        matchIfMissing = false
)
public class BancoTransactionValidator implements TransactionValidator {
    public static final Long CODIGO_BANCO = 785L;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (Objects.isNull(requestTransactionDto.getBeneficiario())) {
            throw new DomainBusinessException("Inválido banco do beneficiário");
        } else if (Objects.isNull(requestTransactionDto.getBeneficiario().getCodigoBanco())
                || requestTransactionDto.getBeneficiario().getCodigoBanco().compareTo(CODIGO_BANCO) != 0
        ) {
            throw new DomainBusinessException("Inválido banco do beneficiário");
        }
    }
}
