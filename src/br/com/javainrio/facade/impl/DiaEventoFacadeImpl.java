package br.com.javainrio.facade.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.javainrio.entidade.DiaEvento;
import br.com.javainrio.facade.DiaEventoFacade;

@Stateless
public class DiaEventoFacadeImpl implements DiaEventoFacade {

	@PersistenceContext(name = "JavaInRio-PU")
	EntityManager em;

	@Override
	public void alterar(DiaEvento p) {
		em.merge(p);
	}

	@Override
	public DiaEvento consultar(DiaEvento p) {
		return em.find(DiaEvento.class, p.getCodigo());
	}

	@Override
	public void excluir(DiaEvento p) {
		p = consultar(p);
		if (p != null) {
			em.remove(p);
		}
	}

	@Override
	public boolean existe(DiaEvento p) {
		return consultar(p) != null;
	}

	@Override
	public void inserir(DiaEvento p) {
		em.persist(p);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<DiaEvento> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(DiaEvento.class));
		return (List<DiaEvento>) em.createQuery(cq).getResultList();
	}

}
