package com.techChallenge.application.cliente;

import com.techChallenge.domain.cliente.Cliente;
import com.techChallenge.domain.cliente.ClienteRepositoryPort;



public class IdentificarClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;

    public IdentificarClienteUseCase(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente executar(String cpf) {
        return clienteRepository.buscarPorCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }
}