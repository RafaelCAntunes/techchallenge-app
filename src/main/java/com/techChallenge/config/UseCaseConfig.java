package com.techChallenge.config;

import com.techChallenge.application.cliente.CadastrarClienteUseCase;
import com.techChallenge.application.cliente.IdentificarClienteUseCase;
import com.techChallenge.application.cliente.ListarClientesUseCase;
import com.techChallenge.application.pedido.*;
import com.techChallenge.application.produto.*;
import com.techChallenge.domain.cliente.ClienteRepositoryPort;
import com.techChallenge.domain.pedido.PagamentoServicePort;
import com.techChallenge.domain.pedido.PedidoRepositoryPort;
import com.techChallenge.domain.produto.ProdutoRepositoryPort;
import com.techChallenge.infra.external.pagamento.PagamentoMockAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public CheckoutPedidoUseCase checkoutPedidoUseCase(PedidoRepositoryPort pedidoRepository, PagamentoMockAdapter pagamentoMock) {
        return new CheckoutPedidoUseCase(pedidoRepository, pagamentoMock);
    }

    @Bean
    public ConsultarStatusPagamentoUseCase consultarStatusPagamentoUseCase(PedidoRepositoryPort pedidoRepository) {
        return new ConsultarStatusPagamentoUseCase(pedidoRepository);
    }

    @Bean
    public AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase(PedidoRepositoryPort pedidoRepository) {
        return new AtualizarStatusPedidoUseCase(pedidoRepository);
    }

    @Bean
    public AtualizarStatusPagamentoUseCase atualizarStatusPagamentoUseCase(PedidoRepositoryPort pedidoRepository) {
        return new AtualizarStatusPagamentoUseCase(pedidoRepository);
    }


    @Bean
    public ListarPedidosUseCase listarPedidosUseCase(PedidoRepositoryPort pedidoRepository) {
        return new ListarPedidosUseCase(pedidoRepository);
    }


    // produto

    @Bean
    public CriarProdutoUseCase criarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new CriarProdutoUseCase(produtoRepositoryPort);
    }

    @Bean
    public BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new BuscarProdutoPorIdUseCase(produtoRepositoryPort);
    }

    @Bean
    public ListarProdutosUseCase listarProdutosUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ListarProdutosUseCase(produtoRepositoryPort);
    }

    @Bean
    public AtualizarProdutoUseCase atualizarProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new AtualizarProdutoUseCase(produtoRepositoryPort);
    }

    @Bean
    public RemoverProdutoUseCase removerProdutoUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new RemoverProdutoUseCase(produtoRepositoryPort);
    }

    @Bean
    public BuscarProdutosPorCategoriaUseCase buscarProdutosPorCategoriaUseCase(ProdutoRepositoryPort produtoRepositoryPort) {
        return new BuscarProdutosPorCategoriaUseCase(produtoRepositoryPort);
    }

    //cliente

    @Bean
    public CadastrarClienteUseCase cadastrarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new CadastrarClienteUseCase(clienteRepositoryPort);
    }

    @Bean
    public IdentificarClienteUseCase identificarClienteUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new IdentificarClienteUseCase(clienteRepositoryPort);
    }

    @Bean
    public ListarClientesUseCase listarClientesUseCase(ClienteRepositoryPort clienteRepositoryPort) {
        return new ListarClientesUseCase(clienteRepositoryPort);
    }


}
