package com.techChallenge.interfaces.controllers;

import com.techChallenge.application.cliente.ListarClientesUseCase;
import com.techChallenge.domain.cliente.Cliente;
import com.techChallenge.interfaces.dto.cliente.ClienteRequestDTO;
import com.techChallenge.interfaces.dto.cliente.ClienteResponseDTO;
import com.techChallenge.application.cliente.CadastrarClienteUseCase;
import com.techChallenge.application.cliente.IdentificarClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClienteController {


    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final IdentificarClienteUseCase identificarClienteUseCase;
    private final ListarClientesUseCase listarClientesUseCase;


    public ClienteController(CadastrarClienteUseCase cadastrarClienteUseCase,
                             IdentificarClienteUseCase identificarClienteUseCase,
                             ListarClientesUseCase listarClientesUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.identificarClienteUseCase = identificarClienteUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
    }

    @Operation(summary = "Cadastrar cliente", description = "Cria um cliente identificado com CPF.")
    @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso")
    @PostMapping("/cadastro")
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody ClienteRequestDTO dto) {
        Cliente cliente = new Cliente( dto.getNome(), dto.getCpf(), dto.getEmail());
        Cliente salvo = cadastrarClienteUseCase.executar(cliente);
        return ResponseEntity.ok(ClienteResponseDTO.fromDomain(salvo));
    }

    @Operation(summary = "Identificar cliente pelo CPF")
    @ApiResponse(responseCode = "200", description = "Cliente identificado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @GetMapping("/identificar/{cpf}")
    public ResponseEntity<ClienteResponseDTO> identificar(@PathVariable String cpf) {
        Cliente cliente = identificarClienteUseCase.executar(cpf);
        return ResponseEntity.ok(ClienteResponseDTO.fromDomain(cliente));
    }

    @Operation(summary = "Lista todos os clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada")
    @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado")
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        List<ClienteResponseDTO> clientes = listarClientesUseCase.executar()
                .stream()
                .map(ClienteResponseDTO::fromDomain)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

}