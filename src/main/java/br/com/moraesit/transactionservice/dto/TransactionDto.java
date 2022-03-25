package br.com.moraesit.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "uuid")
public class TransactionDto {

    private UUID uuid;

    @NotNull(message = "Informar o valor da transação")
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    @NotNull(message = "Informar a conta de origem da transação")
    @Valid
    private Conta conta;

    @NotNull(message = "Informar o beneficiário da transação")
    @Valid
    private BeneficiarioDto beneficiario;

    @NotNull(message = "Informar o tipo da transação")
    private TipoTransacao tipoTransacao;

    private SituacaoEnum situacao;
}
