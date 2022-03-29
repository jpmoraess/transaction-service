package br.com.moraesit.transactionservice.api;

import br.com.moraesit.transactionservice.domain.TransactionBusiness;
import br.com.moraesit.transactionservice.dto.TransactionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionBusiness transactionBusiness;

    public TransactionController(TransactionBusiness transactionBusiness) {
        this.transactionBusiness = transactionBusiness;
    }

    @GetMapping("/{agencia}/{conta}")
    public List<TransactionDto> buscarTransacoes(@PathVariable final Long agencia, @PathVariable final Long conta) {
        return transactionBusiness.findByConta(agencia, conta);
    }
}
