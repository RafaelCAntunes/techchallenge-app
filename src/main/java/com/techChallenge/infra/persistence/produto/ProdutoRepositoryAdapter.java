package com.techChallenge.infra.persistence.produto;

import com.techChallenge.domain.produto.Produto;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {

    private final ProdutoJpaRepository jpaRepository;
    private final ProdutoEntityMapper mapper;

    public ProdutoRepositoryAdapter(ProdutoJpaRepository jpaRepository, ProdutoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity saved = jpaRepository.save(mapper.toEntity(produto));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Produto> listarTodos() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Produto> buscarPorCategoria(String categoria) {
        return jpaRepository.findByCategoria(categoria).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void remover(Long id) {
        jpaRepository.deleteById(id);
    }
}
