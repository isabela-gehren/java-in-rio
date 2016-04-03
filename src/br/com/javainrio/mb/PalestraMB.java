package br.com.javainrio.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.javainrio.entidade.Palestra;
import br.com.javainrio.facade.PalestraFacade;

@ManagedBean(name = "palestraMB")
@RequestScoped
public class PalestraMB implements Serializable {
	private static final long serialVersionUID = 8103328274400432976L;
	private Palestra palestra;
	private List<Palestra> lista = new ArrayList<>();

	@EJB
	private PalestraFacade dao;

	public PalestraMB() {
		palestra = new Palestra();
	}

	public void consultar() {
		int codigoPalestra = palestra.getCodigo();
		palestra = dao.consultar(palestra);

		if (palestra == null || palestra.getCodigo() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Consulta de palestra: ", "Palestra não encontrada:" + codigoPalestra + "!"));
		}
		listar();

	}

	public void excluir() {
		dao.excluir(palestra);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de palestra: ", "Palestra excluída com sucesso!"));
	}

	public Palestra getPalestra() {
		return palestra;
	}

	public List<Palestra> getLista() {
		if ((lista == null) || (lista.size() == 0)) {
			listar();
		}
		return lista;
	}

	public void salvar() {
		try {
			if (palestra.getCodigo() == null || palestra.getCodigo() == 0) {
				incluir();
			} else {
				alterar();
			}
		} catch (Exception e) {
			listar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Alteração de palestra: ", "Já existe uma palestra com esse título!"));
		}
	}	

	public void alterar() {
		dao.alterar(palestra);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de palestra: ", "Palestra alterada com sucesso!"));
	}

	public void incluir() {
		dao.inserir(palestra);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de palestra: ", "Palestra incluída com sucesso!"));
	}

	public void limpar() {
		palestra = new Palestra();
	}

	public void listar() {
		lista = dao.listar();
	}

	public void setPalestra(Palestra p) {
		this.palestra = p;
	}

	public void setLista(List<Palestra> lista) {
		this.lista = lista;
	}
}
