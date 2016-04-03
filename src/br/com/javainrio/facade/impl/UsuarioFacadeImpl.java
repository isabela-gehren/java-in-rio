package br.com.javainrio.facade.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.javainrio.entidade.Usuario;
import br.com.javainrio.facade.UsuarioFacade;
import br.com.javainrio.util.Cripto;

@Stateless
public class UsuarioFacadeImpl implements UsuarioFacade {
	@PersistenceContext(name = "JavaInRio-PU")
	EntityManager em;

	@Override
	public void salvar(Usuario u) {
		u.setSenha(Cripto.Criptografa(u.getSenha()));
		em.merge(u);
	}

	@Override
	public Usuario consultar(Usuario u) {
		return em.find(Usuario.class, u.getCodigo());
	}

	@Override
	public void excluir(Usuario u) {
		u = consultar(u);
		if (u != null) {
			em.remove(u);
		}
	}

	@Override
	public boolean existe(Usuario u) {
		return consultar(u) != null;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Usuario> listar() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(Usuario.class));
		return (List<Usuario>) em.createQuery(cq).getResultList();
	}
}
