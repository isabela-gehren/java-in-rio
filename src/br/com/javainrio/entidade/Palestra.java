
package br.com.javainrio.entidade;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Palestra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Palestra [codigo=" + codigo + ", titulo=" + titulo + ", dataHora=" + dataHora + ", local=" + local
				+ ", palestrante=" + palestrante + ", descricao=" + descricao + "]";
	}
	@Id
	@GeneratedValue
	private Integer codigo;
	
	@Column(unique=true)
	private String titulo;
	private Date dataHora;
	private String local;
	private String palestrante;
	private String descricao;
	
	public Palestra(){}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getPalestrante() {
		return palestrante;
	}
	public void setPalestrante(String palestrante) {
		this.palestrante = palestrante;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}