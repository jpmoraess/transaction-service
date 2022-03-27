package br.com.moraesit.transactionservice.events;

import br.com.moraesit.transactionservice.domain.TransactionBusiness;
import br.com.moraesit.transactionservice.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TransactionConsumer {

    private final ObjectMapper objectMapper;
    private final TransactionBusiness transactionBusiness;

    public TransactionConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
        this.objectMapper = objectMapper;
        this.transactionBusiness = transactionBusiness;
    }

    @KafkaListener(topics = {"${events.consumeTopic}"})
    public void consumeTransaction(String message) {
        try {
            var transaction = getTransaction(message);
            transactionBusiness.update(transaction);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @KafkaListener(topics = {"${events.returnTopic}"})
    public void consumeTransactionExtorno(String message) {
        try {
            var transaction = getTransaction(message);
            log.info("Transação recebida da analise: {}", transaction);

            if (!transaction.isAnalisada()) {
                return;
            } else {
                log.info("Transação analisada: {}", transaction);
                transactionBusiness.approveTransaction(transaction);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws JsonProcessingException {
        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
    }
}
