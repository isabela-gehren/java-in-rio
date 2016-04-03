package br.com.javainrio.dao;

import java.util.List;

import br.com.javainrio.entidade.Usuario;

public interface UsuarioDAO {
	public void salvar(Usuario usuario);

	public Usuario consultar(Usuario usuario);

	public void excluir(Usuario usuario);

	public boolean existe(Usuario usuario);

	public List<Usuario> listar();

}
