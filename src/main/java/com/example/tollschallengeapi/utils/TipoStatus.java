package com.example.tollschallengeapi.utils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoStatus {

	AUTORIZADO, NEGADO, CANCELADO;
	
	@JsonCreator
    public static TipoStatus fromString(String value) {
        for (TipoStatus tpStatus : TipoStatus.values()) {
            if (tpStatus.name().equalsIgnoreCase(value)) {
                return tpStatus;
            }
        }
        throw new IllegalArgumentException("Tipo Status inválido: " + value);
    }
}
