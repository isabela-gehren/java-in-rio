package br.com.javainrio.util;

import java.security.MessageDigest;

public class Cripto {

	public static String Criptografa(String valor) {

		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(valor.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String senhahex = hexString.toString();

			return senhahex;

		} catch (Exception e) {
			return valor;
		}

	}
}
