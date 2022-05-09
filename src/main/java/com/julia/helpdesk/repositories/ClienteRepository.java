package com.julia.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julia.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	

}
