package CapaDatos;

import java.util.List;

public class DTablero {

	private List<DCasilla> casillas;
	
	public DTablero(List<DCasilla> cas) {
		this.casillas = cas;
	}
	
	public List<DCasilla> getCasillas() {
		return casillas;
	}

	public boolean addCasilla(DCasilla cas) {
		return this.casillas.add(cas);
	}
	
	public DCasilla getCasillaJugador(DPersonaje jg) {
		for(DCasilla casilla : casillas) {
			if(casilla.getPj() != null)
				if(casilla.getPj().equals(jg))
					return casilla;
		}
		return null;
	}
}
