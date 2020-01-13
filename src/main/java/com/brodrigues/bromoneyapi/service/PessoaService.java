package com.brodrigues.bromoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brodrigues.bromoneyapi.model.Pessoa;
import com.brodrigues.bromoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = bucarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

		return pessoaRepository.save(pessoaSalva);

	}
	
	
	public void atualizaPropAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoa = bucarPessoaPeloCodigo(codigo);
		pessoa.setAtivo(ativo);
		pessoaRepository.save(pessoa);
	}

	public Pessoa bucarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.findById(codigo).orElse(null);

		if (pessoaSalva == null) {
			ResponseEntity.notFound().build();
			throw new IllegalArgumentException();
		}
		return pessoaSalva;
	}
	
}
