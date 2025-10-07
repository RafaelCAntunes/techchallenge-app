package com.techChallenge.infra.persistence.pedido;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="itens_pedido")
public class ItemPedidoEntity  {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Long produtoId;
private String nomeProduto;
private BigDecimal precoUnitario;
private int quantidade;

}
