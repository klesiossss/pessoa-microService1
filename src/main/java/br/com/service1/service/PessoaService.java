package br.com.service1.service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.service1.exceptions.DuplicatedResourceException;
import br.com.service1.exceptions.ResourceNotFoundException;
import br.com.service1.model.PessoaDTO;



@Service
public class PessoaService  {

	RestTemplate restTemplate = new RestTemplate();
	String urlBase = "http://34.66.3.29/pessoa";
	
	public List<PessoaDTO> obterTodos(){	
		return  Arrays.asList(restTemplate.getForObject(urlBase+"/lista",PessoaDTO[].class));		
	}
	
	
	public List<PessoaDTO> obterTodosPageable(){	
		return Arrays.asList(restTemplate.getForObject(urlBase,PessoaDTO[].class));
	}
	

	public PessoaDTO obterPorId(Long id){
		var pessoa = restTemplate.getForObject(urlBase+"/"+id,PessoaDTO.class);
		if (pessoa ==  null) throw new ResourceNotFoundException();
		
		return pessoa;
	}
	
	public PessoaDTO obterPorCpf(String cpf){
		var pessoa = restTemplate.getForObject(urlBase+"/cpf/"+cpf,PessoaDTO.class);
		if (pessoa == null) throw new ResourceNotFoundException();
		
		return pessoa;
		
	}
	
	public List<PessoaDTO> filtrarPorNome(String nome){
		var pessoas = Arrays.asList(restTemplate.getForObject(urlBase+"/filter/"+"nome/"+nome,PessoaDTO[].class));
		if (pessoas.isEmpty()) throw new ResourceNotFoundException();
		
		return pessoas;
	}
	
	
	public PessoaDTO obterPorNome(String nome){
		var pessoa = restTemplate.getForObject(urlBase+"/nome/"+nome,PessoaDTO.class);
		if (pessoa == null) throw new ResourceNotFoundException();
		
		return pessoa;
	}
	
	
	
	public PessoaDTO salvar(PessoaDTO pessoaDTO) {
		var pessoas = Arrays.asList(restTemplate.getForObject(urlBase+"/lista",PessoaDTO[].class));
		var pessoa = pessoas.stream().filter(p->p.getCpf().equals(pessoaDTO.getCpf())).collect(Collectors.toList());
		
		
		if (pessoa.isEmpty()) restTemplate.postForEntity(urlBase,pessoaDTO,PessoaDTO.class);
		else throw new DuplicatedResourceException();
		
		return pessoaDTO;	
	}
	
	
	public PessoaDTO editar(PessoaDTO pessoaDTO) {
		var pessoas = Arrays.asList(restTemplate.getForObject(urlBase+"/lista",PessoaDTO[].class));
		var pessoa = pessoas.stream().filter(p->p.getCpf().equals(pessoaDTO.getCpf())).collect(Collectors.toList());
		
		if (pessoa.isEmpty()) throw new ResourceNotFoundException();	
			restTemplate.put(urlBase,pessoaDTO,PessoaDTO.class);
		
		return pessoaDTO;	
	}
	
	public void remover(PessoaDTO pessoaDTO) {
		var pessoas = Arrays.asList(restTemplate.getForObject(urlBase+"/lista",PessoaDTO[].class));
		var pessoa = pessoas.stream().filter(p->p.getCpf().equals(pessoaDTO.getCpf())).collect(Collectors.toList());
		HttpEntity<PessoaDTO> httpEntity = new HttpEntity<PessoaDTO>(pessoaDTO);
		if(pessoa.isEmpty()) throw new ResourceNotFoundException();
			restTemplate.delete(urlBase,httpEntity,PessoaDTO.class);		
	}

	
	
}
