package com.techChallenge.application.produto;

import com.techChallenge.domain.produto.ProdutoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RemoverProdutoUseCase {

    private final ProdutoRepositoryPort produtoRepository;

    public RemoverProdutoUseCase(ProdutoRepositoryPort produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void executar(Long id) {
        if (produtoRepository.buscarPorId(id).isEmpty()) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produtoRepository.remover(id);
    }
}
