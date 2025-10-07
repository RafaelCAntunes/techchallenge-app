package com.techChallenge.domain.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(final String nome, final String cpf, final String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
    }
