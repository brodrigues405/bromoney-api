package com.brodrigues.bromoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brodrigues.bromoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
