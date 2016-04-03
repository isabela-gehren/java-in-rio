package br.com.javainrio.facade;

import javax.ejb.Remote;

import br.com.javainrio.dao.PalestraDAO;

@Remote
public interface PalestraFacade extends PalestraDAO {

}
