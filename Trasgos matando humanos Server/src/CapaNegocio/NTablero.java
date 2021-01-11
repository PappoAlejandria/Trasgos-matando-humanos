package CapaNegocio;

import java.util.ArrayList;
import java.util.List;

import CapaDatos.DCasilla;
import CapaDatos.DPersonaje;
import CapaDatos.DTablero;

public class NTablero {

	private DTablero tableroDatos;
	
	public NTablero(DTablero datos) {
		this.tableroDatos = datos;
	}
	
	public DTablero getTableroDatos() {
		return tableroDatos;
	}

	public static NTablero generarTablero(int posXMax, int posYMax) { // Genera un tablero cuadrado. Es un método conveniente.
		int i = 0;
		int j = 0;
		List<DCasilla> casillas = new ArrayList<DCasilla>();
		while(i < posXMax) {
			j=0;
			while(j < posYMax) {
				casillas.add(new DCasilla(i, j));
				j++;
			}
			i++;
		}
		return new NTablero(new DTablero(casillas));
	}
	
	public void ponerPersonaje(DPersonaje pj, int x, int y) { // Pone un personaje en una casilla del tablero. Si no existe la casilla no hace nada.
		for(DCasilla casilla : tableroDatos.getCasillas()) {
			if(casilla.getPosX() == x && casilla.getPosY() == y) {
				casilla.setPj(pj);
			}
		}
	}
	
	public List<DCasilla> getAdyacentes(int x, int y){ // Devuelve las casillas adyacentes a una concreta. Necesario, porque los personajes solamente pueden interactuar con estas casillas.
		List<DCasilla> ady = new ArrayList<DCasilla>();
		
		for(DCasilla casilla : tableroDatos.getCasillas()) {
			if((x-1 <= casilla.getPosX() && casilla.getPosX() <= x+1)
					&& (y-1 <= casilla.getPosY() && casilla.getPosY() <= y+1)
					&& !(casilla.getPosX() == x && casilla.getPosY() == y))
				ady.add(casilla);
		}
		
		return ady;
	}
	
	public void limpiarTablero() { // Quita los personajes de todas las casillas del tablero.
		for(DCasilla casilla : tableroDatos.getCasillas()) {
			casilla.setPj(null);
		}
	}
}
