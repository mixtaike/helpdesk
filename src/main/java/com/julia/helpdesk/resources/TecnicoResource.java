package com.julia.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julia.helpdesk.domain.Tecnico;
import com.julia.helpdesk.domain.dtos.TecnicoDTO;
import com.julia.helpdesk.services.TecnicoService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value="/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value="{id}")
	public ResponseEntity<TecnicoDTO> FindById(@PathVariable Integer id) throws ObjectNotFoundException{
		Tecnico obj = service.findById(id);
		
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
		
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = list.stream(). map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

}
