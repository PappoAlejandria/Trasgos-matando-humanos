package CapaDatos;

import java.util.List;

public class DTablero { // Tablero del juego.

	private List<DCasilla> casillas; // Esta implementación del tablero viene bien para tableros no rectangulares.
	
	public DTablero(List<DCasilla> cas) {
		this.casillas = cas;
	}
	
	public List<DCasilla> getCasillas() {
		return casillas;
	}

	public boolean addCasilla(DCasilla cas) {
		return this.casillas.add(cas);
	}
	
	public DCasilla getCasillaJugador(DPersonaje jg) { // Busca un jugador en el tablero y devuelve su casilla.
		for(DCasilla casilla : casillas) {
			if(casilla.getPj() != null)
				if(casilla.getPj().equals(jg))
					return casilla;
		}
		return null;
	}
}
