package appModel;

import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Tablero;

public class TableroAppModel {
	private int ancho;
	private int alto;
	private Nivel nivel;
	
	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	private Tablero tablero;

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void iniciar(){
		tablero.setAlto(alto);
		tablero.setAncho(ancho);
		tablero.setNivel(nivel);
		
		tablero.iniciar();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
