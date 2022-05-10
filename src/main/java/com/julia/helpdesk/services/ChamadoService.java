package com.julia.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.helpdesk.domain.Chamado;
import com.julia.helpdesk.repositories.ChamadoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	
	public Chamado findById(Integer id) throws ObjectNotFoundException{
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID:" +id));
		
	}


	public List<Chamado> findAll() {
		
		return repository.findAll();
	}

}
