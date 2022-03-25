package br.com.moraesit.transactionservice.repository;

import br.com.moraesit.transactionservice.dto.TransactionDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionDto, UUID> {
}
