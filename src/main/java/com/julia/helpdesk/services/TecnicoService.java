package com.julia.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julia.helpdesk.domain.Pessoa;
import com.julia.helpdesk.domain.Tecnico;
import com.julia.helpdesk.domain.dtos.TecnicoDTO;
import com.julia.helpdesk.repositories.PessoaRepository;
import com.julia.helpdesk.repositories.TecnicoRepository;
import com.julia.helpdesk.services.exceptions.DataIntegrityViolationException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class TecnicoService {
		
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) throws ObjectNotFoundException {
		Optional<Tecnico> objt = repository.findById(id);
		return objt.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
		
	}

	public List<Tecnico> findAll() {
	
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}

	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) throws ObjectNotFoundException {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		}
		
		
		validaPorCpfEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}
	
	
	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf()); 
		
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
		
		
	}

	public void delete(Integer id) throws ObjectNotFoundException {
		Tecnico obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O técnico possui ordens de serviço e não pode ser deletado!");
			
		}
		
		repository.deleteById(id);
		
	}


	
	
}
