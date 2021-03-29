
package br.com.service1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.service1.model.PessoaDTO;
import br.com.service1.service.PessoaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/service1")
@AllArgsConstructor
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@GetMapping("/lista")
	public ResponseEntity<List<PessoaDTO>> obterTodos() {
		var pessoas = pessoaService.obterTodos();
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping()
	public ResponseEntity<List<PessoaDTO>> obterTodosPageable() {
		var pessoas = pessoaService.obterTodos();
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> obterPorId(@PathVariable Long id) {
		var pessoa = pessoaService.obterPorId(id);
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<PessoaDTO> obterPorCpf(@PathVariable String cpf) {
		var pessoa = pessoaService.obterPorCpf(cpf);
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("filter/nome/{nome}")
	public ResponseEntity<List<PessoaDTO>> filtrarPorNome(@PathVariable String nome) {
		var pessoa = pessoaService.filtrarPorNome(nome);
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<PessoaDTO> obterPorNome(@PathVariable String nome) {
		var pessoa = pessoaService.obterPorNome(nome);
		return ResponseEntity.ok(pessoa);
	}	
	
	@PostMapping
	public ResponseEntity<PessoaDTO> salvar(@RequestBody PessoaDTO pessoaDTO) {
		var pessoa = pessoaService.salvar(pessoaDTO);
		return ResponseEntity.ok(pessoa);
	}
	
	
	@PutMapping
	public ResponseEntity<PessoaDTO> editar(@RequestBody PessoaDTO pessoaDTO) {
		var pessoa = pessoaService.editar(pessoaDTO);
		return ResponseEntity.ok(pessoa);	
	}
	
	@DeleteMapping
	public ResponseEntity<PessoaDTO> remover(@RequestBody PessoaDTO pessoaDTO) {
		pessoaService.remover(pessoaDTO);
		return ResponseEntity.ok().body(pessoaDTO);
	}

}