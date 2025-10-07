package com.techChallenge.application.produto;

import com.techChallenge.domain.produto.Produto;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;


public class BuscarProdutoPorIdUseCase {

    private final ProdutoRepositoryPort produtoRepository;

    public BuscarProdutoPorIdUseCase(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(Long id) {
        return produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }
}