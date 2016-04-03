package br.com.javainrio.facade;

import java.util.List;

import javax.ejb.Remote;

import br.com.javainrio.dao.PalestraDAO;
import br.com.javainrio.entidade.Palestra;

@Remote
public interface PalestraFacade extends PalestraDAO {

	List<Palestra> listarDoAnoCorrente();

}
