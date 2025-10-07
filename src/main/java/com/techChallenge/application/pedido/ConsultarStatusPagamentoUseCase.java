package com.techChallenge.application.pedido;

import com.techChallenge.domain.pedido.PagamentoStatus;
import com.techChallenge.domain.pedido.PedidoRepositoryPort;

public class ConsultarStatusPagamentoUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public ConsultarStatusPagamentoUseCase(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PagamentoStatus executar(Long pedidoId) {
        return pedidoRepository.buscarPorId(pedidoId)
                .map(p -> p.getPagamentoStatus())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }
}