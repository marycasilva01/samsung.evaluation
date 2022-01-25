package com.br.samsung.evaluation.dataprovider.client.model.enums;

import java.util.stream.Stream;

public enum Currency {

    USD (1L, "USD", "Dolar"),
    PEN (2L, "PEN", "Soles Peruano"),
    BRL (3L, "BRL", "Real");

    private final Long id;
    private final String codigo;
    private final String descricao;

    Currency(Long id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Currency fromCodigo(String codigo) {
        return Stream.of(Currency.values()).filter(t -> t.getCodigo().equals(codigo)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
