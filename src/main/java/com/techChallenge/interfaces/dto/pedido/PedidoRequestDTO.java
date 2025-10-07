package com.techChallenge.interfaces.dto.pedido;

import com.techChallenge.interfaces.dto.itemPedido.ItemPedidoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Dados para criar um pedido (checkout)")
public class PedidoRequestDTO {

    @Schema(description = "ID do cliente", example = "3")
    private Long clienteId;

    @Schema(description = "Lista de itens do pedido")
    private List<ItemPedidoDTO> itens;


}