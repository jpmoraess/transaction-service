package br.com.moraesit.transactionservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(of = {"codigoAgencia", "codigoConta"})
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Informar o código da agência.")
    private Long codigoAgencia;

    @NotNull(message = "Informar o código da conta.")
    private Long codigoConta;
}
