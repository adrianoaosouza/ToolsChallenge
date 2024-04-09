package com.example.tollschallengeapi.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Table(name = "descricao_transacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DescricaoTransacao {

	@JsonProperty("valor")
	private String valor;
	
	@JsonProperty("datahora")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	//@NonNull
	private LocalDateTime datahora;
	
	@JsonProperty("estabelecimento")
	private String estabelecimento;
	
	@JsonProperty("nsu")
	private String nsu;
	
	@JsonProperty("codigoAutorizacao")
	private String codigoAutorizacao;
	
	@JsonProperty("status")
	private String status;

}
