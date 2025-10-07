package com.techChallenge.application.pedido;

import com.techChallenge.domain.pedido.PagamentoStatus;
import com.techChallenge.domain.pedido.Pedido;
import com.techChallenge.domain.pedido.PedidoRepositoryPort;


public class AtualizarStatusPagamentoUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public AtualizarStatusPagamentoUseCase(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public void executar(Long pedidoId, PagamentoStatus novoStatus) {
        Pedido pedido = pedidoRepository.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedido.setPagamentoStatus(novoStatus);
        pedidoRepository.salvar(pedido);
    }
}
