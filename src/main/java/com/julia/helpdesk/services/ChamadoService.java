package com.julia.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.helpdesk.domain.Chamado;
import com.julia.helpdesk.domain.Cliente;
import com.julia.helpdesk.domain.Tecnico;
import com.julia.helpdesk.domain.dtos.ChamadoDTO;
import com.julia.helpdesk.domain.enums.Prioridade;
import com.julia.helpdesk.domain.enums.Status;
import com.julia.helpdesk.repositories.ChamadoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) throws ObjectNotFoundException{
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! ID:" +id));
		
	}


	public List<Chamado> findAll() {
		
		return repository.findAll();
	}


	public Chamado create(@Valid ChamadoDTO objDTO) throws ObjectNotFoundException {
		
		return repository.save(newChamado(objDTO));
	}
	
	private Chamado newChamado(ChamadoDTO obj) throws ObjectNotFoundException {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
			
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
		
	}

}
