package CapaDatos;

import java.util.ArrayList;
import java.util.List;

public class DJugador {

	private String nombre;
	private List<DPersonaje> pjs; // Aunque sea una lista, el programa solamente soporta un jugador por simplicidad.
	private int iniciativa; // La iniciativa determina qué jugador va primero

	public DJugador(String nombre) {
		this.nombre = nombre;
		this.pjs = new ArrayList<DPersonaje>();
	}
	
	public List<DPersonaje> getPjs() {
		return pjs;
	}
	
	public void addPj(DPersonaje pj) {
		this.pjs.add(pj);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIniciativa() {
		return iniciativa;
	}

	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}
}
