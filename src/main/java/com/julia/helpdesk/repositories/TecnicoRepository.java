package com.julia.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julia.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
	

}
