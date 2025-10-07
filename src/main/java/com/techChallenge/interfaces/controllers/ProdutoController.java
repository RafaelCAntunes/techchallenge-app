package com.techChallenge.interfaces.controllers;

import com.techChallenge.application.produto.*;
import com.techChallenge.domain.produto.Produto;
import com.techChallenge.interfaces.dto.produto.ProdutoRequestDTO;
import com.techChallenge.interfaces.dto.produto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
public class ProdutoController {

    private final CriarProdutoUseCase criarProdutoUseCase;
    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final RemoverProdutoUseCase removerProdutoUseCase;
    private final BuscarProdutosPorCategoriaUseCase buscarProdutosPorCategoriaUseCase;

    public ProdutoController(CriarProdutoUseCase criarProdutoUseCase,
                             BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase,
                             ListarProdutosUseCase listarProdutosUseCase,
                             AtualizarProdutoUseCase atualizarProdutoUseCase,
                             RemoverProdutoUseCase removerProdutoUseCase,
                             BuscarProdutosPorCategoriaUseCase buscarProdutosPorCategoriaUseCase) {
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.buscarProdutoPorIdUseCase = buscarProdutoPorIdUseCase;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.atualizarProdutoUseCase = atualizarProdutoUseCase;
        this.removerProdutoUseCase = removerProdutoUseCase;
        this.buscarProdutosPorCategoriaUseCase = buscarProdutosPorCategoriaUseCase;
    }

    @Operation(summary = "Criar produto")
    @ApiResponse(responseCode = "200", description = "Produto criado com sucesso")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = new Produto(dto.getNome(), dto.getDescricao(), dto.getPreco(), dto.getCategoria());
        Produto salvo = criarProdutoUseCase.executar(produto);
        return ResponseEntity.ok(ProdutoResponseDTO.fromDomain(salvo));
    }

    @Operation(summary = "Listar todos os produtos")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        List<ProdutoResponseDTO> produtos = listarProdutosUseCase.executar()
                .stream()
                .map(ProdutoResponseDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Buscar produto por ID")
    @ApiResponse(responseCode = "200", description = "Produto encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        Produto produto = buscarProdutoPorIdUseCase.executar(id);
        return ResponseEntity.ok(ProdutoResponseDTO.fromDomain(produto));
    }

    @Operation(summary = "Buscar produtos por categoria")
    @ApiResponse(responseCode = "200", description = "Produtos encontrados por categoria")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(@PathVariable String categoria) {
        List<ProdutoResponseDTO> produtos = buscarProdutosPorCategoriaUseCase.executar(categoria)
                .stream()
                .map(ProdutoResponseDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Atualizar produto")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        Produto produto = new Produto( dto.getNome(), dto.getDescricao(), dto.getPreco(), dto.getCategoria());
        Produto atualizado = atualizarProdutoUseCase.executar(id, produto);
        return ResponseEntity.ok(ProdutoResponseDTO.fromDomain(atualizado));
    }

    @Operation(summary = "Remover produto")
    @ApiResponse(responseCode = "204", description = "Produto removido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        removerProdutoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
