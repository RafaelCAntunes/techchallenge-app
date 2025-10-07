package com.techChallenge.interfaces.dto.pedido;

import com.techChallenge.domain.pedido.Pedido;
import com.techChallenge.domain.pedido.StatusPedido;
import com.techChallenge.interfaces.dto.itemPedido.ItemPedidoDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PedidoResponseDTO {

    private Long id;
    private Long clienteId;
    private StatusPedido status;
    private List<ItemPedidoDTO> itens;
    private long tempoDeEsperaEmMinutos;

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.clienteId = pedido.getClienteId();
        this.status = pedido.getStatus();
        this.tempoDeEsperaEmMinutos = calcularTempoEspera(pedido);

        this.itens = pedido.getItens().stream()
                .map(item -> {
                    ItemPedidoDTO dto = new ItemPedidoDTO();
                    dto.setProdutoId(item.getProdutoId());
                    dto.setNomeProduto(item.getNomeProduto());
                    dto.setPrecoUnitario(item.getPrecoUnitario());
                    dto.setQuantidade(item.getQuantidade());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private long calcularTempoEspera(Pedido pedido) {
        return java.time.Duration.between(pedido.getCriadoEm(), java.time.LocalDateTime.now()).toMinutes();
    }

    

}
