package br.com.service1.model;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
public class PessoaDTO implements Serializable {
	
	private static final long serialVersionUID = 3574475229098202153L;
	
	
	private Long id;
	
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;
	private Genero genero;
	private String nacionalidade;
	private String naturalidade;
	
	
}
