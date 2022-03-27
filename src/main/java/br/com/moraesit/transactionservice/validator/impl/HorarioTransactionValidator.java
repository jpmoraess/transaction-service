package br.com.moraesit.transactionservice.validator.impl;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.exception.DomainBusinessException;
import br.com.moraesit.transactionservice.validator.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(
        value = "${transaction.validation.horario}",
        havingValue = "true",
        matchIfMissing = false
)
public class HorarioTransactionValidator implements TransactionValidator {

    public static final int HOUR_END = 18;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        if (LocalDateTime.now().getHour() > HOUR_END || requestTransactionDto.getData().getHour() > HOUR_END)
            throw new DomainBusinessException("Horário permitido até as 18:00");
    }
}
