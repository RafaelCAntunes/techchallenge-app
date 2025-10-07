package com.techChallenge.infra.persistence.pedido;

import com.techChallenge.domain.pedido.PagamentoStatus;
import com.techChallenge.domain.pedido.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="pedidos")
public class PedidoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long clienteId;

        private LocalDateTime criadoEm;

        @Enumerated(EnumType.STRING)
        private StatusPedido status;

        @Enumerated(EnumType.STRING)
        private PagamentoStatus pagamentoStatus;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "pedido_id")
        private List<ItemPedidoEntity> itens;


}
