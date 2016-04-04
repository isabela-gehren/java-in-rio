package br.com.javainrio.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Usuario autenticar(String email, String senha) {
		String senhaCripto = Cripto.Criptografa(senha);

		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery cq = qb.createQuery();
		Root<Usuario> root = cq.from(Usuario.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(qb.equal(root.get("email"), email));
		predicates.add(qb.equal(root.get("senha"), senhaCripto));

		cq.select(root).where(predicates.toArray(new Predicate[] {}));
		// execute query and do something with result
		// em.createQuery(cq).getResultList();

		try {
			return (Usuario) em.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			return null;
		}

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
