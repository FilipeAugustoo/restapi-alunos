package br.com.ceat.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aluno {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 20)
	private String matricula;
	
	@NotBlank
	@Size(max = 20)
	private String serieAno;
	
}
