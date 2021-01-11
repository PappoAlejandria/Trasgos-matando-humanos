package CapaDatos;

public class DDado {

	private int cantidadDados;
	private int numeroDados;
	
	public DDado(int cant, int num) {
		this.cantidadDados = cant;
		this.numeroDados = num;
	}

	public int getCantidadDados() {
		return cantidadDados;
	}

	public int getNumeroDados() {
		return numeroDados;
	}
	public String toString() {
		return this.cantidadDados + "d" + this.numeroDados;
	}
}
