package com.techChallenge.domain.pedido;

public interface PagamentoServicePort {
    PagamentoStatus iniciarPagamento(Pedido pedido);
}
