package com.techChallenge.application.pedido;

import com.techChallenge.domain.pedido.Pedido;
import com.techChallenge.domain.pedido.PedidoRepositoryPort;
import com.techChallenge.domain.pedido.StatusPedido;

import java.util.Comparator;
import java.util.List;

public class ListarPedidosUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public ListarPedidosUseCase(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> executar() {
        return pedidoRepository.listarPedidosNaoFinalizados()
                .stream()
                .sorted(Comparator
                        .comparing((Pedido p) -> prioridadeStatus(p.getStatus()))
                        .thenComparing(Pedido::getCriadoEm))
                .toList();
    }

    private int prioridadeStatus(StatusPedido status) {
        return switch (status) {
            case PRONTO -> 1;
            case EM_PREPARO -> 2;
            case RECEBIDO -> 3;
            default -> 4;
        };
    }
}
