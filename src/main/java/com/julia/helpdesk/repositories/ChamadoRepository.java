package com.julia.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julia.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
	

}
