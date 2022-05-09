package com.julia.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.helpdesk.domain.Tecnico;
import com.julia.helpdesk.repositories.TecnicoRepository;

@Service
public class TecnicoService {
		
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> objt = repository.findById(id);
		return objt.orElseThrow(null);
		
	}
	
	
	
}
