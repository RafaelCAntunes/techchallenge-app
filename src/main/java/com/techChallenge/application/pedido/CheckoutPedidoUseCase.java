package com.techChallenge.application.pedido;

import com.techChallenge.domain.pedido.*;

import java.util.List;

public class CheckoutPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final PagamentoServicePort pagamentoService;

    public CheckoutPedidoUseCase(PedidoRepositoryPort pedidoRepository, PagamentoServicePort pagamentoService) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoService = pagamentoService;
    }

    public Long executar(Long clienteId, List<ItemPedido> itens) {
        Pedido pedido = new Pedido(clienteId,itens);
        pedido = pedidoRepository.salvar(pedido);
        PagamentoStatus status = pagamentoService.iniciarPagamento(pedido);
        pedido.setPagamentoStatus(status);
        pedidoRepository.salvar(pedido);
        return pedido.getId();
    }
}
