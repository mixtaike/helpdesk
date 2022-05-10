package com.julia.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.julia.helpdesk.domain.Cliente;
import com.julia.helpdesk.domain.Pessoa;
import com.julia.helpdesk.domain.dtos.ClienteDTO;
import com.julia.helpdesk.repositories.ClienteRepository;
import com.julia.helpdesk.repositories.PessoaRepository;
import com.julia.helpdesk.services.exceptions.DataIntegrityViolationException;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {
		
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> objt = repository.findById(id);
		return objt.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado Id: "+id));
		
	}

	public List<Cliente> findAll() {
	
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) throws ObjectNotFoundException {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
	
	
	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		
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
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O técnico possui ordens de serviço e não pode ser deletado!");
			
		}
		
		repository.deleteById(id);
		
	}


	
	
}
