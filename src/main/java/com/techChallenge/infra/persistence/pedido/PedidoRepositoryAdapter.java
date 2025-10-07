package com.techChallenge.infra.persistence.pedido;

import com.techChallenge.domain.pedido.Pedido;
import com.techChallenge.domain.pedido.PedidoRepositoryPort;
import com.techChallenge.domain.pedido.StatusPedido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository jpaRepository;
    private final PedidoEntityMapper mapper;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpaRepository,
                                   PedidoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoEntity entity = mapper.toEntity(pedido);
        PedidoEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Pedido> listarPedidosNaoFinalizados() {
        return jpaRepository.findByStatusNot(StatusPedido.FINALIZADO)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
