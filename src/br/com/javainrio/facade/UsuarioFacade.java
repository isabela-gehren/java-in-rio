package br.com.javainrio.facade;

import javax.ejb.Remote;

import br.com.javainrio.dao.UsuarioDAO;

@Remote
public interface UsuarioFacade extends UsuarioDAO {

}
