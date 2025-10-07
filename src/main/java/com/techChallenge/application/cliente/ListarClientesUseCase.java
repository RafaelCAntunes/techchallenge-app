package com.techChallenge.application.cliente;

import com.techChallenge.domain.cliente.Cliente;
import com.techChallenge.domain.cliente.ClienteRepositoryPort;

import java.util.List;

public class ListarClientesUseCase {

    private final ClienteRepositoryPort clienteRepository;

    public ListarClientesUseCase(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> executar() {
        return clienteRepository.listarTodos();
    }
}