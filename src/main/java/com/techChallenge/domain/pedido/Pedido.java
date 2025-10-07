package com.techChallenge.domain.pedido;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Long id;
    private Long clienteId;
    private List<ItemPedido> itens;
    private StatusPedido status;
    private PagamentoStatus pagamentoStatus;
    private LocalDateTime criadoEm;

    public Pedido(Long clienteId, List<ItemPedido> itens) {
        this.clienteId = this.clienteId;
        this.itens = itens;
        this.status = StatusPedido.RECEBIDO;
        this.pagamentoStatus = PagamentoStatus.AGUARDANDO;
        this.criadoEm = LocalDateTime.now();
    }

    public void atualizarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
    }

    public void atualizarPagamento(PagamentoStatus status) {
        this.pagamentoStatus = status;
    }

}
