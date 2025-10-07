package com.techChallenge.domain.pedido;

public enum StatusPedido {
    PRONTO(1),
    EM_PREPARO(2),
    RECEBIDO(3),
    FINALIZADO(4),
    CANCELADO(5);

    private final int prioridade;

    StatusPedido(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return prioridade;
    }
}
