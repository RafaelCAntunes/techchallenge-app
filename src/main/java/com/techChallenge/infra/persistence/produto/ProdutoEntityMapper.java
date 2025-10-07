package com.techChallenge.infra.persistence.produto;

import com.techChallenge.domain.produto.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoEntityMapper {

    public Produto toDomain(ProdutoEntity entity) {
        return new Produto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getCategoria()
        );
    }

    public ProdutoEntity toEntity(Produto produto) {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(produto.getId());
        entity.setNome(produto.getNome());
        entity.setDescricao(produto.getDescricao());
        entity.setPreco(produto.getPreco());
        entity.setCategoria(produto.getCategoria());
        return entity;
    }
}
