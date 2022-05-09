package com.julia.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julia.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	

}
