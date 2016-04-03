package br.com.javainrio.facade.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.javainrio.entidade.Palestra;
import br.com.javainrio.facade.PalestraFacade;

@Stateless
public class PalestraFacadeImpl implements PalestraFacade {

	@PersistenceContext(name = "JavaInRio-PU")
	EntityManager em;

	@Override
	public void alterar(Palestra p) {
		em.merge(p);
	}

	@Override
	public Palestra consultar(Palestra p) {
		return em.find(Palestra.class, p.getCodigo());
	}

	@Override
	public void excluir(Palestra p) {
		p = consultar(p);
		if (p != null) {
			em.remove(p);
		}
	}

	@Override
	public boolean existe(Palestra p) {
		return consultar(p) != null;
	}

	@Override
	public void inserir(Palestra p) {
		em.persist(p);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Palestra> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Palestra.class));
		return (List<Palestra>) em.createQuery(cq).getResultList();
	}

}
