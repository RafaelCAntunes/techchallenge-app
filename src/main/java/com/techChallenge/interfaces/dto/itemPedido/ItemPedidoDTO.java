package com.techChallenge.interfaces.dto.itemPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Item do pedido")
public class ItemPedidoDTO {

    @Schema(description = "ID do produto", example = "2")
    private Long produtoId;

    @Schema(description = "Nome do produto", example = "X-Burguer")
    private String nomeProduto;

    @Schema(description = "Preço unitário do item", example = "19.90")
    private BigDecimal precoUnitario;

    @Schema(description = "Quantidade", example = "2")
    private int quantidade;

}