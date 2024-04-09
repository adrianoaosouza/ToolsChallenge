package com.example.tollschallengeapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tollschallengeapi.domain.TransacaoRequest;
import com.example.tollschallengeapi.domain.TransacaoResponse;
import com.example.tollschallengeapi.exceptions.TransacaoNaoAutorizadaException;
import com.example.tollschallengeapi.service.TransacaoService;



@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

	@Autowired
	private TransacaoService transacaoService;

	@PostMapping("/pagamento")
	
	public ResponseEntity<?> realizarPagamento(@RequestBody TransacaoRequest body) {
		try {
			TransacaoResponse novaTransacao = transacaoService.realizarPagamento(body.getTransacao());
			return ResponseEntity.ok(novaTransacao);
		} catch (TransacaoNaoAutorizadaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping("/estorno/{id}")
	public ResponseEntity<?> realizarEstorno(@PathVariable Long id) {
		try {
			TransacaoResponse transacao = transacaoService.realizarEstorno(id);
			return ResponseEntity.ok(transacao);			
		} catch (TransacaoNaoAutorizadaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}
	
	@GetMapping("/consulta")
	public ResponseEntity<List<TransacaoResponse>> consultarTodasTransacoes() {
		try {
			List<TransacaoResponse> transacoes = transacaoService.consultarTransacoes();
			return ResponseEntity.ok(transacoes);
		} catch (TransacaoNaoAutorizadaException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/consulta/{id}")
	public ResponseEntity<?> consultarTransacao(@PathVariable Long id) {
		try {
			TransacaoResponse transacao = transacaoService.consultarTransacaoPorId(id);
			return ResponseEntity.ok(transacao);
		} catch (TransacaoNaoAutorizadaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}


}
