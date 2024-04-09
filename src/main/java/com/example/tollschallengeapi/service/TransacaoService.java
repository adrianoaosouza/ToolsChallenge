package com.example.tollschallengeapi.service;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.tollschallengeapi.domain.DescricaoTransacao;
import com.example.tollschallengeapi.domain.Transacao;
import com.example.tollschallengeapi.domain.TransacaoResponse;
import com.example.tollschallengeapi.exceptions.TransacaoNaoAutorizadaException;
import com.example.tollschallengeapi.repository.TransacaoRepository;
import com.example.tollschallengeapi.utils.TipoPagamento;
import com.example.tollschallengeapi.utils.TipoStatus;

@Component
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	public TransacaoResponse realizarPagamento(Transacao transacao) {
		TransacaoResponse transacaoResp = new TransacaoResponse();
		processarTransacao(transacao, transacaoResp);
		return transacaoResp;
	}
	
	public TransacaoResponse realizarEstorno(Long id) {
		TransacaoResponse transacaoResp = new TransacaoResponse();
		processarEstorno(id, transacaoResp);
		return transacaoResp;
	}

	
	private Transacao processarTransacao(Transacao transacao, TransacaoResponse transacaoResp) {

		if (transacaoRepository.existsById(transacao.getId())) {
	        throw new TransacaoNaoAutorizadaException("Transação não autorizada - O ID da transação deve ser único");
	    }

		DescricaoTransacao newDescricao = new DescricaoTransacao();
		newDescricao.setValor(transacao.getDescricao().getValor());
		newDescricao.setDatahora(transacao.getDescricao().getDatahora());
		newDescricao.setEstabelecimento(transacao.getDescricao().getEstabelecimento());
		newDescricao.setNsu("1234567890");
		newDescricao.setCodigoAutorizacao("147258369");
		newDescricao.setStatus(TipoStatus.AUTORIZADO.toString());		
		transacao.setDescricao(newDescricao);
		transacaoResp.setTransacao(transacao);
	
		validarStatus(transacao);	
		validarTipoPagamento(transacao);

		return transacaoRepository.save(transacao);
	}


	private void validarStatus(Transacao transacao) {
		TipoStatus tpStatus = TipoStatus.fromString(transacao.getDescricao().getStatus());
		if (!Arrays.asList(TipoStatus.values()).contains(tpStatus)) {
	        throw new TransacaoNaoAutorizadaException("Transação não autorizada - Status inválido");
	    }
	}

	private void validarTipoPagamento(Transacao transacao) {
		List<TipoPagamento> tpPagamentoList = Arrays.asList(TipoPagamento.values());			
		List<String> tpPagamentoStringList = tpPagamentoList.stream().map(Enum::toString).collect(Collectors.toList());		
				
		if (!tpPagamentoStringList.contains(transacao.getFormaPagamento().getTipoPagamento())) {    
			throw new TransacaoNaoAutorizadaException("Transação não autorizada - Forma de Pagamento inválida");
		}	
	}



	public Transacao processarEstorno(Long id, TransacaoResponse transacaoResp) {
		Transacao transacao = transacaoRepository.findById(id)
				.orElseThrow(() -> new TransacaoNaoAutorizadaException("Transacao não encontrada"));
		transacao.getDescricao().setStatus(TipoStatus.CANCELADO.toString());
		transacaoResp.setTransacao(transacao);
		Transacao savedTransacao = transacaoRepository.save(transacao);		
		return savedTransacao;	
	}

	public TransacaoResponse consultarTransacaoPorId(Long id) {
		TransacaoResponse transacaoResp = new TransacaoResponse();
		Transacao transacao = transacaoRepository.findById(id)
				.orElseThrow(() -> new TransacaoNaoAutorizadaException("Transação não encontrada"));
		transacaoResp.setTransacao(transacao);
		return transacaoResp;
	}

	public List<TransacaoResponse> consultarTransacoes() {
		List<Transacao> transacoes = transacaoRepository.findAll();
		List<TransacaoResponse> listaTransacoes = transacoes.stream()
				.map(transacao -> {
					TransacaoResponse transacaoResponse = new TransacaoResponse();
					transacaoResponse.setTransacao(transacao);
					return transacaoResponse;
				}).collect(Collectors.toList());
		return listaTransacoes;
	}

}
