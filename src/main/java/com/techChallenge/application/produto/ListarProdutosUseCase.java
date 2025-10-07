package com.techChallenge.application.produto;


import com.techChallenge.domain.produto.Produto;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;

import java.util.List;

public class ListarProdutosUseCase {

    private final ProdutoRepositoryPort produtoRepository;

    public ListarProdutosUseCase(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> executar() {
        return produtoRepository.listarTodos();
    }
}
