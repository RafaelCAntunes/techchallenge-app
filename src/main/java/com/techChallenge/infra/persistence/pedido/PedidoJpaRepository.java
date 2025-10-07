package com.techChallenge.infra.persistence.pedido;

import com.techChallenge.domain.pedido.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {

    List<PedidoEntity> findByStatusNot(StatusPedido status);
}