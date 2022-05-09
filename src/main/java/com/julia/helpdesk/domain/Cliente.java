package com.julia.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.julia.helpdesk.domain.enums.Perfil;

@Entity

public class Cliente extends Pessoa{
	private static final long serialVersionUID = 1L;
	
	//um cliente para muitos chamados
		@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}
	//toda pessoa tem um chamado
	//poliformofismo, tem metodos proprios
	//herdou da classe pessoa porém tem metodos e atributos que a classe pessoa n tem

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
