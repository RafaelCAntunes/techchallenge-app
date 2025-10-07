package com.techChallenge.infra.external.pagamento;

import com.techChallenge.domain.pedido.PagamentoServicePort;
import com.techChallenge.domain.pedido.PagamentoStatus;
import com.techChallenge.domain.pedido.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMockAdapter implements PagamentoServicePort {

    @Override
    public PagamentoStatus iniciarPagamento(Pedido pedido) {
        return PagamentoStatus.AGUARDANDO;
    }

}
