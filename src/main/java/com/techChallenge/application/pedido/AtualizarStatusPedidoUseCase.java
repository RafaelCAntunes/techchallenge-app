package com.techChallenge.application.pedido;

import com.techChallenge.domain.pedido.Pedido;
import com.techChallenge.domain.pedido.PedidoRepositoryPort;
import com.techChallenge.domain.pedido.StatusPedido;

public class AtualizarStatusPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public AtualizarStatusPedidoUseCase(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(Long pedidoId, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        pedidoRepository.salvar(pedido);
    }
}
