package com.brodrigues.bromoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brodrigues.bromoneyapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
