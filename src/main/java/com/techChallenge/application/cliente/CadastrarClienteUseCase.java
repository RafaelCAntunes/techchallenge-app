package com.techChallenge.application.cliente;


import com.techChallenge.domain.cliente.Cliente;
import com.techChallenge.domain.cliente.ClienteRepositoryPort;


public class CadastrarClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;

    public CadastrarClienteUseCase(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente executar(Cliente cliente) {
        if (clienteRepository.buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF");
        }
        return clienteRepository.salvar(cliente);
    }
}