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

	public static NTablero generarTablero(int posXMax, int posYMax) {
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
	
	public void ponerPersonaje(DPersonaje pj, int x, int y) {
		for(DCasilla casilla : tableroDatos.getCasillas()) {
			if(casilla.getPosX() == x && casilla.getPosY() == y) {
				casilla.setPj(pj);
			}
		}
	}
	
	public List<DCasilla> getAdyacentes(int x, int y){
		List<DCasilla> ady = new ArrayList<DCasilla>();
		
		for(DCasilla casilla : tableroDatos.getCasillas()) {
			if((x-1 <= casilla.getPosX() && casilla.getPosX() <= x+1)
					&& (y-1 <= casilla.getPosY() && casilla.getPosY() <= y+1)
					&& !(casilla.getPosX() == x && casilla.getPosY() == y))
				ady.add(casilla);
		}
		
		return ady;
	}
	
	public void limpiarTablero() {
		for(DCasilla casilla : tableroDatos.getCasillas()) {
			casilla.setPj(null);
		}
	}
}
