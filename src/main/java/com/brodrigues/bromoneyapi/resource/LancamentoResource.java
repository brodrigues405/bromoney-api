package com.brodrigues.bromoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brodrigues.bromoneyapi.event.RecursoCriadoEvent;
import com.brodrigues.bromoneyapi.exceptionHandler.bromoneyExceptionHandler.Erro;
import com.brodrigues.bromoneyapi.model.Lancamento;
import com.brodrigues.bromoneyapi.repository.LancamentoRepository;
import com.brodrigues.bromoneyapi.service.LancamentoService;
import com.brodrigues.bromoneyapi.service.exception.PessoaInexistenteOuInativaException;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	public List<Lancamento> buscarLancamentos(){
		
		return lancamentoRepository.findAll();

	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscaLancamentoPorId(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoRepository.findById(codigo).orElse(null);

		return lancamento != null ? ResponseEntity.ok().body(lancamento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {

		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlerPessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("pessoa-inativa-ou-inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolverdor = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolverdor));
		
		return ResponseEntity.badRequest().body(erros);
		
	}
	
	
	

}
