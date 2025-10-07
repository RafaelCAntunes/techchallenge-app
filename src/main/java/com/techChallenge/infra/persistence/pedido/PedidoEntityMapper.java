package com.techChallenge.infra.persistence.pedido;

import com.techChallenge.domain.pedido.ItemPedido;
import com.techChallenge.domain.pedido.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoEntityMapper {

    public PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setClienteId(pedido.getClienteId());
        entity.setCriadoEm(pedido.getCriadoEm());
        entity.setStatus(pedido.getStatus());
        entity.setPagamentoStatus(pedido.getPagamentoStatus());

        List<ItemPedidoEntity> itens = pedido.getItens().stream().map(this::toEntity).collect(Collectors.toList());
        entity.setItens(itens);
        return entity;
    }

    public Pedido toDomain(PedidoEntity entity) {
        List<ItemPedido> itens = entity.getItens().stream().map(this::toDomain).collect(Collectors.toList());

        return new Pedido(
                entity.getId(),
                entity.getClienteId(),
                itens,
                entity.getStatus(),
                entity.getPagamentoStatus(),
                entity.getCriadoEm()
        );
    }

    private ItemPedidoEntity toEntity(ItemPedido item) {
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.setProdutoId(item.getProdutoId());
        entity.setNomeProduto(item.getNomeProduto());
        entity.setPrecoUnitario(item.getPrecoUnitario());
        entity.setQuantidade(item.getQuantidade());
        return entity;
    }

    private ItemPedido toDomain(ItemPedidoEntity entity) {
        return new ItemPedido(
                entity.getProdutoId(),
                entity.getNomeProduto(),
                entity.getQuantidade(),
                entity.getPrecoUnitario()
        );
    }
}
