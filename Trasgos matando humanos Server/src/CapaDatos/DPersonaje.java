package CapaDatos;

public class DPersonaje {

	private DDado dmgPj;
	private int vidaPj;
	private int movimientos;
	
	private int movRestantes;
	private int vidaRestante;
	private boolean haAtacado;
	
	
	public DPersonaje(DDado dmg, int vida, int movimientos) {
		this.dmgPj = dmg;
		this.vidaPj = vida;
		this.movimientos = movimientos;
		this.movRestantes = this.getMovimientos();
		this.vidaRestante = this.getVidaPj();
		this.haAtacado = false;
	}
	
	public DDado getDmgPj() {
		return dmgPj;
	}

	public void setDmgPj(DDado dmgPj) {
		this.dmgPj = dmgPj;
	}

	public int getVidaPj() {
		return vidaPj;
	}

	public void setVidaPj(int vidaPj) {
		this.vidaPj = vidaPj;
	}

	public int getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}

	public int getMovRestantes() {
		return movRestantes;
	}

	public void setMovRestantes(int movRestantes) {
		this.movRestantes = movRestantes;
	}
	
	public int getVidaRestante() {
		return vidaRestante;
	}

	public void setVidaRestante(int vidaRestante) {
		this.vidaRestante = vidaRestante;
	}

	public boolean isHaAtacado() {
		return haAtacado;
	}

	public void setHaAtacado(boolean haAtacado) {
		this.haAtacado = haAtacado;
	}
	
	public String toString() {
		return "Daño: " + this.dmgPj.toString() + ", Vida: " + this.vidaRestante + "/" + this.vidaPj + ", Movimientos: " + this.movRestantes + "/" + this.movimientos + ". " + (this.haAtacado ? "Ya ha atacado" : "No ha atacado");
	}
}
