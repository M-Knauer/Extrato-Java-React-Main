package br.com.banco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTA")
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column(name = "NOME_RESPONSAVEL", nullable = false)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	@Setter(value = AccessLevel.NONE)
	private List<Transferencia> tranferencias = new ArrayList<>();
}
