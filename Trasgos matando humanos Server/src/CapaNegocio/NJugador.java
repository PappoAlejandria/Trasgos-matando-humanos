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

	public void rollIniciativa() {
		this.datos.setIniciativa((new NDado(new DDado(1, 20))).rollD());
	}
}
