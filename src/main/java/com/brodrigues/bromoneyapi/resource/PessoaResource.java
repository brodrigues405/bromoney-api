package com.brodrigues.bromoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brodrigues.bromoneyapi.event.RecursoCriadoEvent;
import com.brodrigues.bromoneyapi.model.Pessoa;
import com.brodrigues.bromoneyapi.repository.PessoaRepository;
import com.brodrigues.bromoneyapi.service.PessoaService;

@RestController
@RequestMapping("/{pessoas}")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoasRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@GetMapping
	public List<Pessoa> listarPessoas(){
		return pessoasRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		
		Pessoa pessoaCadastrada = pessoasRepository.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaCadastrada.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaCadastrada);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPorID(@PathVariable Long codigo){
				Pessoa pessoa = pessoasRepository.findById(codigo).orElse(null);
		return pessoa != null? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Pessoa removerPessoa(@PathVariable Long codigo) {
		
		Pessoa pessoa = pessoasRepository.findById(codigo).orElse(null);
		
		if(pessoa == null) {
			ResponseEntity.notFound().build();
		}
		pessoasRepository.delete(pessoa);
		 return pessoa;
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {

		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);

		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizaPropAtivo(codigo, ativo);
	}
	
	

}
