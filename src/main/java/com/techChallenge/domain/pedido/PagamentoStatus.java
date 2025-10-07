package com.techChallenge.domain.pedido;

import java.util.Optional;

public enum PagamentoStatus {
    AGUARDANDO, APROVADO, RECUSADO;


    public static Optional<PagamentoStatus> fromString(String value) {
        if (value == null) return Optional.empty();
        try {
            return Optional.of(PagamentoStatus.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
