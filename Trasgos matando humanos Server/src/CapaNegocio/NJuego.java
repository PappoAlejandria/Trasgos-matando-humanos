package CapaNegocio;

import java.util.List;

import CapaDatos.DCasilla;
import CapaDatos.DPersonaje;

public class NJuego {

	private NTablero tablero;
	private NJugador gm;
	private NJugador jugador;
	
	// 0 si es turno del gm, 1 si es turno del jugador
	private int turno;
	
	private boolean acabado;

	public NJuego(NTablero tablero, NJugador gm, NJugador jgdr) {
		this.tablero = tablero;
		this.turno = 0;
		this.gm=gm;
		this.jugador=jgdr;
		this.rollIniciativas();
	}

	public void setJugador(NJugador jugador) {
		this.jugador = jugador;
	}

	public void setGm(NJugador gm) {
		this.gm = gm;
	}
	
	public void rollIniciativas() {
		jugador.rollIniciativa();
		gm.rollIniciativa();
		if(gm.getDatos().getIniciativa() > jugador.getDatos().getIniciativa())
			turno = 0;
		else
			turno = 1;
	}
	
	public void hacerUnMovimiento(int casilla) {
		NJugador jgdrTurno = (turno == 0) ? gm : jugador;
		DPersonaje pjTurno = jgdrTurno.getDatos().getPjs().get(0);
		DCasilla casillaActual = tablero.getTableroDatos().getCasillaJugador(pjTurno);
		List<DCasilla> adyacentes = tablero.getAdyacentes(casillaActual.getPosX(),
				casillaActual.getPosY());
		if(casilla < adyacentes.size()) {
			if(adyacentes.get(casilla).getPj() == null) {
				if(pjTurno.getMovRestantes() > 0) {
					adyacentes.get(casilla).setPj(pjTurno);
					casillaActual.setPj(null);
					pjTurno.setMovRestantes(pjTurno.getMovRestantes()-1);
				}
			}
			else {
				if(!pjTurno.isHaAtacado()) {
					int dmg = (new NDado(pjTurno.getDmgPj())).rollD();
					adyacentes.get(casilla).getPj().setVidaRestante(adyacentes.get(casilla).getPj().getVidaRestante()-dmg);
					if(adyacentes.get(casilla).getPj().getVidaRestante() <= 0)
						this.acabado = true;
					pjTurno.setHaAtacado(true);
				}
			}
		}
	}
	
	public void pasarTurno() {
		this.turno = Math.abs(this.turno - 1);
		for(DPersonaje pj : gm.getDatos().getPjs()) {
			pj.setHaAtacado(false);
			pj.setMovRestantes(pj.getMovimientos());
		}
		for(DPersonaje pj : jugador.getDatos().getPjs()) {
			pj.setHaAtacado(false);
			pj.setMovRestantes(pj.getMovimientos());
		}
	}
	
	public boolean isAcabado() {
		return acabado;
	}

	public int getTurno() {
		return turno;
	}
}
