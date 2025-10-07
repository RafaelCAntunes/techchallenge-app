package com.techChallenge.application.produto;


import com.techChallenge.domain.produto.Produto;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;


import java.util.List;


public class BuscarProdutosPorCategoriaUseCase {

    private final ProdutoRepositoryPort produtoRepository;

    public BuscarProdutosPorCategoriaUseCase(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> executar(String categoria) {
        return produtoRepository.buscarPorCategoria(categoria);
    }

}
