package br.com.javainrio.facade;

import javax.ejb.Remote;

import br.com.javainrio.dao.UsuarioDAO;
import br.com.javainrio.entidade.Usuario;

@Remote
public interface UsuarioFacade extends UsuarioDAO {
	Usuario autenticar(String email, String senha);
}
