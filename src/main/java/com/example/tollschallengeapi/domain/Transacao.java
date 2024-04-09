package com.example.tollschallengeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transacao {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("cartao")
    private String cartao;

    @Embedded
    @JsonProperty("descricao")
    private DescricaoTransacao descricao;

    @Embedded
    @JsonProperty("formaPagamento")
    private FormaPagamento formaPagamento;
    

}
