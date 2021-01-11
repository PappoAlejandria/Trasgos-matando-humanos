package CapaNegocio;

import CapaDatos.DPersonaje;

public class NPersonaje { // Wrapper en capa de negocio

	private DPersonaje datos;

	public NPersonaje(DPersonaje datos) {
		this.datos = datos;
	}

	public DPersonaje getDatos() {
		return datos;
	}
}
