package br.com.moraesit.transactionservice.domain;

import br.com.moraesit.transactionservice.dto.RequestTransactionDto;
import br.com.moraesit.transactionservice.dto.TransactionDto;
import br.com.moraesit.transactionservice.repository.TransactionRepository;
import br.com.moraesit.transactionservice.validator.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
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

    public void update(final TransactionDto transactionDto) {
        log.info("Atualizando transação: {}", transactionDto);
        transactionRepository.save(transactionDto);
    }

    public void approveTransaction(final TransactionDto transactionEvent) {
        var transactionDtoOptional = getTransaction(transactionEvent);
        if (transactionDtoOptional.isPresent()) {
            var transactionDB = transactionDtoOptional.get();
            if (!transactionDB.isAnalisada() && transactionEvent.isAnalisada()) {
                transactionDB.aprovar();
                update(transactionDB);
                log.info("Transação aprovada: {}", transactionDB);
            }
        }
    }

    public Optional<TransactionDto> getTransaction(final TransactionDto transactionDto) {
        return transactionRepository.findById(transactionDto.getUuid());
    }
}
