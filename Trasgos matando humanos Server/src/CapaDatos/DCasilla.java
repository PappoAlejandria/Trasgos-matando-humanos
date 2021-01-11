package CapaDatos;

public class DCasilla {
	private final int PosX;
	private final int PosY;
	
	private DPersonaje pj;
	
	public DCasilla(int x, int y) {
		this.PosX = x;
		this.PosY = y;
		this.pj = null;
	}
	
	public DCasilla(int x, int y, DPersonaje pj) {
		this.PosX = x;
		this.PosY = y;
		this.pj = pj;
	}

	public int getPosX() {
		return PosX;
	}

	public int getPosY() {
		return PosY;
	}

	public DPersonaje getPj() {
		return pj;
	}

	public void setPj(DPersonaje pj) {
		this.pj = pj;
	}
	
	public String toString() {
		return "(" + this.PosX + "," + this.PosY + ")";
	}
}
