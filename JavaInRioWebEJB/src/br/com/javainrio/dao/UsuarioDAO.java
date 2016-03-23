package br.com.javainrio.dao;

import br.com.javainrio.entidade.Usuario;

public interface UsuarioDAO {
	void inserir(Usuario usuario);
	void alterar(Usuario usuario);
}
