package com.julia.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.julia.helpdesk.domain.enums.Perfil;

//classe abstrata não permite instanciar objetos dessa classe
// como outros perfis herdarão dessa classe seus atributos não podem ser PRIVATE
//o acesso aos atributos serão pelo modificador de acesso PROTECTED

@Entity
public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String nome;
	
	@CPF
	//não existira dois cpfs com os mesmos valores
	@Column(unique=true)
	protected String cpf;
	//não existira dois emails com os mesmos valores
	@Column(unique=true)
	protected String email;
	protected String senha;
	
	
	//permite que o user tenha varios tipos de perfil
	//de acordo com os perfis criados no enum de PERFIL
	//HASHSET= para evitar excecao de ponteiro NULL null pointer exception
	//HASHSET= Não permite que tenha dois valores iguais dentro da lista,ex user ter dois perfil cliente
	//é uma coleção do tipo Inetger, quando buscar um id no banco ela virá uma lista de perfis
	//junto do usuario, serve pra assegurar q a lista de perfis no GET virá junto com o usuarios
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	//terá uma tabela no banco de dados que será criada, contendo somente os perfis 
	protected Set<Integer> perfis = new HashSet<>();
	
	//estou informando o modelo que a data de criação terá ao ser salva no banco
	@JsonFormat(pattern="dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	
	
	
	

}
