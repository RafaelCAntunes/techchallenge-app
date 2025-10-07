package com.techChallenge.interfaces.dto.cliente;

import com.techChallenge.domain.cliente.Cliente;
import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;

    public static ClienteResponseDTO fromDomain(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCpf());
        dto.setEmail(cliente.getEmail());
        return dto;
    }
}