package br.com.javainrio.mb;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.javainrio.entidade.Palestra;
import br.com.javainrio.entidade.Programacao;
import br.com.javainrio.facade.PalestraFacade;

@ManagedBean(name = "programacaoMB")
@RequestScoped
public class ProgramacaoMB implements Serializable {
	private static final long serialVersionUID = -366213260053512672L;
	private List<Programacao> lista = new ArrayList<>();

	@EJB
	private PalestraFacade dao;
	
	public ProgramacaoMB(){
		
	}

	public List<Programacao> getLista() {
		if(lista.isEmpty())
			listar();
		
		return lista;
	}
	
	private void listar(){

		List<Palestra> palestras = dao.listarDoAnoCorrente();


		for (Palestra p : palestras) {
			Date dia = new Date(p.getDataHora().getYear(), p.getDataHora().getMonth(), p.getDataHora().getDate());
			
			LocalDate dateCurrent = p.getDataHora().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if (lista.stream().filter(p2 -> p2.getDia() == dia).count() == 0) {
				List<Palestra> palestrasDoDia = new ArrayList<>();
				palestrasDoDia.add(p);
				Programacao prog = new Programacao(dia, palestrasDoDia);
				lista.add(prog);
			}
			else{
				lista.stream().filter(p2 -> p2.getDia() == dia).findFirst().get().AdicionarPalestra(p);
			}

		}
	}
	
	public void setLista(List<Programacao> lista) {
		this.lista = lista;
	}
	
	public List<Palestra> listaPorData(Date data) {
		return dao.listaDoDia(data);
	}
}
