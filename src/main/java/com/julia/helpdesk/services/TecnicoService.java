package com.julia.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.helpdesk.domain.Tecnico;
import com.julia.helpdesk.repositories.TecnicoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class TecnicoService {
		
	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) throws ObjectNotFoundException {
		Optional<Tecnico> objt = repository.findById(id);
		return objt.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
		
	}
	
	
	
}
