package br.com.moraesit.transactionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BeneficiarioDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Informar o CPF")
    private Long CPF;

    @NotNull(message = "Informar o código do banco de destino")
    private Long codigoBanco;

    @NotNull(message = "Informar a agência de destino")
    private String agencia;

    @NotNull(message = "Informar a conta de destino")
    private String conta;

    @NotNull(message = "Informar o nome do favorecido")
    private String nomeFavorecido;
}
