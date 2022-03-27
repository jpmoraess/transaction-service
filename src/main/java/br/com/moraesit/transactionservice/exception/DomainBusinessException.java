package br.com.moraesit.transactionservice.exception;

public class DomainBusinessException extends RuntimeException {
    public DomainBusinessException(String message) {
        super(message);
    }
}
