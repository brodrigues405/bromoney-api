package com.brodrigues.bromoneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brodrigues.bromoneyapi.model.Lancamento;
import com.brodrigues.bromoneyapi.model.Pessoa;
import com.brodrigues.bromoneyapi.repository.LancamentoRepository;
import com.brodrigues.bromoneyapi.repository.PessoaRepository;
import com.brodrigues.bromoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {

		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);

		if (pessoa == null || pessoa.getAtivo() == false) {
			throw new PessoaInexistenteOuInativaException();
		}

		return lancamentoRepository.save(lancamento);
	}

	
	
}
