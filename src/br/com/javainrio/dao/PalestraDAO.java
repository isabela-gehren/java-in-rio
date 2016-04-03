package br.com.javainrio.dao;

import java.util.List;

import br.com.javainrio.entidade.Palestra;


public interface PalestraDAO {

	public void alterar(Palestra ingrediente);

	public Palestra consultar(Palestra ingrediente);

	public void excluir(Palestra ingrediente);

	public boolean existe(Palestra ingrediente);

	public void inserir(Palestra ingrediente);

	public List<Palestra> listar();

}