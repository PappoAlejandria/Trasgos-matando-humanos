package CapaNegocio;

import java.util.Random;

import CapaDatos.DDado;

public class NDado {

	private DDado datos;
	
	public NDado(DDado datos) {
		this.datos = datos;
	}
	
	public int rollD() { // Tira los dados y da la suma de sus tiradas.
		int resultado = 0;
		int numeroDados = datos.getNumeroDados();
		int i = 0;
		Random rnd = new Random();
		while(i<datos.getCantidadDados()) {
			resultado += rnd.nextInt(numeroDados)+1;
			i++;
		}
		return resultado;
	}
}
