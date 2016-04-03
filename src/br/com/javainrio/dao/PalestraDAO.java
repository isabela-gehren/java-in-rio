package br.com.javainrio.dao;

import java.util.List;

import br.com.javainrio.entidade.Palestra;


public interface PalestraDAO {

	public void alterar(Palestra palestra);

	public Palestra consultar(Palestra palestra);

	public void excluir(Palestra palestra);

	public boolean existe(Palestra palestra);

	public void inserir(Palestra palestra);

	public List<Palestra> listar();

}