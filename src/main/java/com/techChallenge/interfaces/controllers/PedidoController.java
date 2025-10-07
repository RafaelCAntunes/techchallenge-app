package com.techChallenge.interfaces.controllers;

import com.techChallenge.application.pedido.*;
import com.techChallenge.domain.pedido.ItemPedido;
import com.techChallenge.domain.pedido.PagamentoStatus;
import com.techChallenge.domain.pedido.Pedido;
import com.techChallenge.domain.pedido.StatusPedido;
import com.techChallenge.interfaces.dto.pedido.PedidoRequestDTO;
import com.techChallenge.interfaces.dto.pedido.PedidoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Operações relacionadas a pedidos")
public class PedidoController {

    private final CheckoutPedidoUseCase checkoutPedidoUseCase;
    private final ConsultarStatusPagamentoUseCase consultarStatusPagamentoUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final AtualizarStatusPagamentoUseCase atualizarStatusPagamentoUseCase;
    private final ListarPedidosUseCase listarPedidosUseCase;

    public PedidoController(CheckoutPedidoUseCase checkoutPedidoUseCase,
                            ConsultarStatusPagamentoUseCase consultarStatusPagamentoUseCase,
                            AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase,
                            AtualizarStatusPagamentoUseCase atualizarStatusPagamentoUseCase,
                            ListarPedidosUseCase listarPedidosUseCase) {
        this.checkoutPedidoUseCase = checkoutPedidoUseCase;
        this.consultarStatusPagamentoUseCase = consultarStatusPagamentoUseCase;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
        this.atualizarStatusPagamentoUseCase = atualizarStatusPagamentoUseCase;
        this.listarPedidosUseCase = listarPedidosUseCase;
    }

    @Operation(summary = "Fake checkout", description = "Cria um pedido")
    @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso")
    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestBody PedidoRequestDTO request) {
        List<ItemPedido> itens = request.getItens().stream()
                .map(i -> new ItemPedido(i.getProdutoId(),i.getNomeProduto(), i.getQuantidade(), i.getPrecoUnitario()))
                .toList();

        Long pedidoId = checkoutPedidoUseCase.executar(request.getClienteId(), itens);

        Map<String, Object> response = new HashMap<>();
        response.put("pedidoId", pedidoId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Consulta Pagamento", description = "verifica o status do pagamento")
    @ApiResponse(responseCode = "200", description = "status do pagamento")
    @GetMapping("/{pedidoId}/status-pagamento")
    public ResponseEntity<Map<String, Object>> consultarStatusPagamento(@PathVariable Long pedidoId) {
        PagamentoStatus status = consultarStatusPagamentoUseCase.executar(pedidoId);
        return ResponseEntity.ok(Map.of("statusPagamento", status));
    }


    @Operation(summary = "Listar pedidos em andamento", description = "Pedidos com status diferente de FINALIZADO.")
    @ApiResponse(responseCode = "200", description = "Pedidos em andamento retornados com sucesso")
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<Pedido> pedidos = listarPedidosUseCase.executar();
         List<PedidoResponseDTO> dtos = pedidos.stream().map(PedidoResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }



    @Operation(summary = "Atualiza status do pedido", description = "RECEBIDO → EM_PREPARO → PRONTO → FINALIZADO")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @PostMapping("/{pedidoId}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long pedidoId,  @RequestParam StatusPedido status) {
        atualizarStatusPedidoUseCase.executar(pedidoId, status);
        return ResponseEntity.ok().build();
    }

    // Webhook simulado
    @Operation(summary = "Atualiza status de pagamento", description = "Atualiza status de pagamento para aprovado")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @PostMapping("/pagamento/webhook")
    public ResponseEntity<Void> webhookPagamento(@RequestBody Map<String, Object> payload) {
        Long pedidoId = Long.valueOf(payload.get("pedidoId").toString());
        Optional<PagamentoStatus> status = PagamentoStatus.fromString(payload.get("status").toString());

        if (status.isEmpty()) {
            return ResponseEntity.badRequest().build();

        }
        atualizarStatusPagamentoUseCase.executar(pedidoId, status.get());
        atualizarStatusPedidoUseCase.executar(pedidoId, "APROVADO".equals(status.get()) ? StatusPedido.EM_PREPARO : StatusPedido.RECEBIDO);
        return ResponseEntity.ok().build();
    }


}