package br.com.moraesit.transactionservice.domain;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.repository.TransactionRepository;
import br.com.moraesit.transactionservice.validator.TransactionValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionBusiness {

    private final TransactionRepository transactionRepository;
    private final TransactionValidation transactionValidation;


    public TransactionBusiness(TransactionRepository transactionRepository, TransactionValidation transactionValidation) {
        this.transactionRepository = transactionRepository;
        this.transactionValidation = transactionValidation;
    }

    public void save(final RequestTransactionDto requestTransactionDto) {
        if (Objects.isNull(requestTransactionDto.getData()))
            requestTransactionDto.setData(LocalDateTime.now());
        transactionValidation.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }
}
