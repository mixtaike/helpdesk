package com.julia.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.julia.helpdesk.domain.Chamado;
import com.julia.helpdesk.domain.Cliente;
import com.julia.helpdesk.domain.Tecnico;
import com.julia.helpdesk.domain.enums.Perfil;
import com.julia.helpdesk.domain.enums.Prioridade;
import com.julia.helpdesk.domain.enums.Status;
import com.julia.helpdesk.repositories.ChamadoRepository;
import com.julia.helpdesk.repositories.ClienteRepository;
import com.julia.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{

	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico();
		tec1.setNome("Valdir Cesar");
		tec1.setCpf("760.457.770-93");
		tec1.setEmail("valdir@mail.com");
		tec1.setSenha("123");
		tec1.addPerfil(Perfil.ADMIN);
		
		
		Cliente cli1 = new Cliente(null,"Linux Trovalds","994595945945","torvalds@mail.com","123");
		
		Chamado c1 = new Chamado(null,Prioridade.MEDIA,Status.ANDAMENTO,"Chamado 01","Primeiro chamado",tec1,cli1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
		
	}
	
	

}
