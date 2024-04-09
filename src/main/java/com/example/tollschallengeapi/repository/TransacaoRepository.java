package com.example.tollschallengeapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tollschallengeapi.domain.Transacao;


public interface TransacaoRepository extends JpaRepository <Transacao, Long> {

	@SuppressWarnings("unchecked")
	Transacao save(Transacao transacao);
	
	Optional<Transacao> findById(Long Id);
	
	List<Transacao> findAll();
	
	boolean existsById(Long Id);
	
}
