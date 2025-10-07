package com.techChallenge.domain.produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    Produto salvar(Produto produto);
    Optional<Produto> buscarPorId(Long id);
    List<Produto> listarTodos();
    List<Produto> buscarPorCategoria(String categoria);
    void remover(Long id);
}
