package br.com.moraesit.transactionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Informar o código da agência.")
    private Long codigoAgencia;

    @NotNull(message = "Informar o código da conta.")
    private Long codigoConta;
}
