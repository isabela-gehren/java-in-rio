package br.com.javainrio.entidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Programacao {

	private Date dia;
	//private String diaFormatado;
	private List<Palestra> palestras;
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public String getDiaFormatado(){
		return new SimpleDateFormat("dd/MM/yyyy").format(this.dia);
	}
	public List<Palestra> getPalestras() {
		return palestras;
	}
	public void setPalestras(List<Palestra> palestras) {
		this.palestras = palestras;
	}
	
	public Programacao(Date dia, List<Palestra> palestras)
	{
		this.dia = dia;
		this.palestras = palestras;
	}
	public void AdicionarPalestra(Palestra palestra){
		if(this.palestras == null)
			this.palestras = new ArrayList<>();
		this.palestras.add(palestra);
	}
}
