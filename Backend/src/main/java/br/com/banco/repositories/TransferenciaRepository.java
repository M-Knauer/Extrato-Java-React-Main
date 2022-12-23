package br.com.banco.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	
	@Query("SELECT obj FROM Transferencia obj "
			+ "WHERE obj.conta = :conta_id AND obj.dataTransferencia BETWEEN :min AND :max AND (obj.nome = :nome OR ISNULL(:nome, '') = '')")
	Page<Transferencia> findTransferencias(Conta conta_id, LocalDate min, LocalDate max, String nome, Pageable pageable);
	
}
