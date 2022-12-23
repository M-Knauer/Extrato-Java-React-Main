package br.com.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	TransferenciaService ts;
	
	@GetMapping(path = "/conta")
	public Page<Transferencia> findTransferencias(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "id", defaultValue = "1") Conta conta_id, 
			Pageable pageable ) {
		
		return ts.findTransferencias(conta_id, minDate, maxDate, nome, pageable);
	}
}
