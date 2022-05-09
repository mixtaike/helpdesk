package com.julia.helpdesk.domain;

import java.time.LocalDate;

import com.julia.helpdesk.domain.enums.Prioridade;
import com.julia.helpdesk.domain.enums.Status;

public class Chamado {
	
	private Integer id;
	private LocalDate dataAbertura = LocalDate.now();
	private LocalDate dataFechamento;
	private Prioridade prioridade;
	private Status status;
	private String observacoes;
	private String titulo;
	
	

}
