package com.techChallenge.interfaces.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Dados para cadastrar um cliente")
public class ClienteRequestDTO {

    @Schema(description = "Nome completo", example = "João da Silva")
    private String nome;

    @Schema(description = "E-mail do cliente", example = "joao@email.com")
    private String email;

    @Schema(description = "CPF do cliente (usado apenas na gestao de cliente)", example = "12345678900")
    private String cpf;

}
