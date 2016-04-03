package br.com.javainrio.dao;

import java.util.List;

import br.com.javainrio.entidade.DiaEvento;


public interface DiaEventoDAO {

	public void alterar(DiaEvento diaEvento);

	public DiaEvento consultar(DiaEvento diaEvento);

	public void excluir(DiaEvento diaEvento);

	public boolean existe(DiaEvento diaEvento);

	public void inserir(DiaEvento diaEvento);

	public List<DiaEvento> listar();

}