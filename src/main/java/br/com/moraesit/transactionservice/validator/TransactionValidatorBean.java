package br.com.moraesit.transactionservice.validator;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.exception.DomainBusinessException;
import br.com.moraesit.transactionservice.validator.impl.BancoTransactionValidator;
import br.com.moraesit.transactionservice.validator.impl.HorarioTransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled:true}")
public class TransactionValidatorBean implements TransactionValidation {

    private List<TransactionValidator> transactionValidatorList = new ArrayList<>();

    @PostConstruct
    public void addBeans() {
        transactionValidatorList.add(new BancoTransactionValidator());
        transactionValidatorList.add(new HorarioTransactionValidator());
    }

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBusinessException {
        transactionValidatorList.stream().forEach(transactionValidator -> {
            transactionValidator.validate(requestTransactionDto);
        });
    }
}
