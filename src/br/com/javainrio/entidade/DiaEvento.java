package br.com.javainrio.entidade;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class DiaEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer codigo;
	
	private Date data;
	
	private Double preco;
	
	public DiaEvento() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}
	
	public Date getData() {
		return this.data;
	}
	
	public Double getPreco() {
		return this.preco;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
