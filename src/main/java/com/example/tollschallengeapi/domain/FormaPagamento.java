package com.example.tollschallengeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "forma_pagamento") 
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormaPagamento {

	@JsonProperty("tipo")
	private String TipoPagamento;	
	
	@JsonProperty("parcelas")
    private int parcelas;
	
}
