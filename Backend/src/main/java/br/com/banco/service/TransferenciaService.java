package br.com.banco.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {

	
	@Autowired
	TransferenciaRepository tr;

	@Transactional(readOnly = true)
	public Page<Transferencia> findTransferencias(Conta conta_id, String minDate, String maxDate, String nome, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate min = minDate.isEmpty() ? today.minusYears(5) : LocalDate.parse(minDate);
		LocalDate max = maxDate.isEmpty() ? today : LocalDate.parse(maxDate);
		
		return tr.findTransferencias(conta_id, min, max, nome, pageable);
	}
	
}
