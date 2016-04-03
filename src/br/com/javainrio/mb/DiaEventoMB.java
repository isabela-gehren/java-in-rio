package br.com.javainrio.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.javainrio.entidade.DiaEvento;
import br.com.javainrio.facade.DiaEventoFacade;

@ManagedBean(name = "diaEventoMB")
@RequestScoped
public class DiaEventoMB implements Serializable {
	private static final long serialVersionUID = 8103328274400432976L;
	private DiaEvento diaEvento;
	private List<DiaEvento> lista = new ArrayList<>();

	@EJB
	private DiaEventoFacade dao;

	public DiaEventoMB() {
		diaEvento = new DiaEvento();
	}

	public void consultar() {
		int codigoDiaEvento = diaEvento.getCodigo();
		diaEvento = dao.consultar(diaEvento);

		if (diaEvento == null || diaEvento.getCodigo() == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Consulta de diaEvento: ", "DiaEvento não encontrada:" + codigoDiaEvento + "!"));
		}
		listar();

	}

	public void excluir() {
		dao.excluir(diaEvento);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de diaEvento: ", "DiaEvento excluída com sucesso!"));
	}

	public DiaEvento getDiaEvento() {
		return diaEvento;
	}

	public List<DiaEvento> getLista() {
		if ((lista == null) || (lista.size() == 0)) {
			listar();
		}
		return lista;
	}

	public void salvar() {
		try {
			if (diaEvento.getCodigo() == null || diaEvento.getCodigo() == 0) {
				incluir();
			} else {
				alterar();
			}
		} catch (Exception e) {
			listar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Alteração de diaEvento: ", "Já existe uma diaEvento com esse título!"));
		}
	}	

	public void alterar() {
		dao.alterar(diaEvento);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de diaEvento: ", "DiaEvento alterada com sucesso!"));
	}

	public void incluir() {
		dao.inserir(diaEvento);
		listar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Alteração de diaEvento: ", "DiaEvento incluída com sucesso!"));
	}

	public void limpar() {
		diaEvento = new DiaEvento();
	}

	public void listar() {
		lista = dao.listar();
	}

	public void setDiaEvento(DiaEvento p) {
		this.diaEvento = p;
	}

	public void setLista(List<DiaEvento> lista) {
		this.lista = lista;
	}
}
