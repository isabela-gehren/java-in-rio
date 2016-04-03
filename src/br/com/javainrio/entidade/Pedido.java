package br.com.javainrio.entidade;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer codigo;
	
	private Integer usuarioId;
	
	public Pedido() {
	}

	
}