package com.example.tollschallengeapi.utils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoPagamento {

	AVISTA, PARCELADO_LOJA, PARCELADO_EMISSOR;


	@JsonCreator
    public static TipoPagamento fromString(String value) {
        for (TipoPagamento tpPagamento : TipoPagamento.values()) {
            if (tpPagamento.name().equalsIgnoreCase(value)) {
                return tpPagamento;
            }
        }
        throw new IllegalArgumentException("Tipo Pagamento inválido: " + value);
    }
}
