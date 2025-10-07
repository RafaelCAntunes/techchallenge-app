package com.techChallenge.application.produto;

import com.techChallenge.domain.produto.Produto;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;


public class CriarProdutoUseCase {

    private final ProdutoRepositoryPort produtoRepository;

    public CriarProdutoUseCase(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(Produto produto) {
        return produtoRepository.salvar(produto);
    }
}
