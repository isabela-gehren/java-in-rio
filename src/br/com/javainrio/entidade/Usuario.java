package br.com.javainrio.entidade;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigo;

	private String nome;

	@Column(unique = true)
	private String email;

	private String senha;

	@Column(length = 11, unique = true)
	private String cpf;

	private String bairro;

	private String endereco;

	@Column(length = 8)
	private String cep;

	private String cidade;

	@Column(length = 2)
	private String estado;

	@Column(updatable = false)
	private Boolean admin;

	public Usuario() {
		this.codigo = UUID.randomUUID().toString();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getAdmin() {
		return admin;
	}
}
