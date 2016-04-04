package br.com.javainrio.util;

import java.util.List;

import br.com.javainrio.entidade.*;

public class PalestraUtils {
	
	public static String getPalestraLista(List<Palestra> lista) {
		String texto = "";
		
		for (Palestra palestra : lista) {
			texto += palestra.getTitulo() + "\r\n";
		}
		
		return texto;
	}
}
