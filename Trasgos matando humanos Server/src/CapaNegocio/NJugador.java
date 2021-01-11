package CapaNegocio;

import CapaDatos.DDado;
import CapaDatos.DJugador;

public class NJugador {

	private DJugador datos;
	
	public NJugador(DJugador datos) {
		this.datos=datos;
	}
	
	public DJugador getDatos() {
		return datos;
	}

	public void rollIniciativa() { // La iniciativa determina qué jugador va primero
		this.datos.setIniciativa((new NDado(new DDado(1, 20))).rollD());
	}
}
