package br.com.javainrio.facade;

import javax.ejb.Remote;

import br.com.javainrio.dao.DiaEventoDAO;

@Remote
public interface DiaEventoFacade extends DiaEventoDAO {

}