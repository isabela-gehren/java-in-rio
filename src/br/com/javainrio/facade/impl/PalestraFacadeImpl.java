package br.com.javainrio.facade.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.*;

import br.com.javainrio.entidade.Palestra;
import br.com.javainrio.facade.PalestraFacade;
import br.com.javainrio.util.DateUtils;

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

	@Override
	public List<Palestra> listarDoAnoCorrente() {
		List<Palestra> tudo = listar();
		List<Palestra> palestrasDoAnoCorrente = tudo.stream().filter(p -> p.getDataHora().getYear() == new Date().getYear()).collect(Collectors.toList());
		return (List<Palestra>)palestrasDoAnoCorrente;
	}
	
	@Override
	public List<Palestra> listaDoDia(Date data) {
		List<Palestra> tudo = listar();
		List<Palestra> filtro = new ArrayList<Palestra>();
		
		for (final Palestra palestra : tudo) {
			if (DateUtils.getZeroTimeDate(palestra.getDataHora()).compareTo(DateUtils.getZeroTimeDate(data)) == 0) {
				filtro.add(palestra);
			}
		}
		
		return (List<Palestra>)filtro;
	}
}
