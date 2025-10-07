package com.techChallenge.application.produto;

import com.techChallenge.domain.produto.Produto;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;


public class AtualizarProdutoUseCase {

    private final ProdutoRepositoryPort produtoRepository;

    public AtualizarProdutoUseCase(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto executar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());

        return produtoRepository.salvar(produtoExistente);
    }
}
