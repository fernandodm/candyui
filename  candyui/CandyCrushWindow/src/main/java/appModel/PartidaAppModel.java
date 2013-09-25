package appModel;

import java.util.ArrayList;
import java.util.Arrays;

import org.uqbar.commons.utils.Observable;

import Tp.CandyCrush.Abajo;
import Tp.CandyCrush.Arriba;
import Tp.CandyCrush.Coordenada;
import Tp.CandyCrush.Derecha;
import Tp.CandyCrush.Izquierda;
import Tp.CandyCrush.Movimiento;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Partida;
import Tp.CandyCrush.Tablero;

@Observable
public class PartidaAppModel {
	
	private Partida partida;
	private ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) Arrays.asList(new Arriba(),new Derecha(),new Abajo(), new Izquierda());
    private Coordenada coordenadaActual;
    private Movimiento movimientoARealizar;
    private Tablero tablero = partida.getNivelActual().getTablero();
    private int puntaje = partida.getNivelActual().getPuntaje();
    private Nivel nivelActual = partida.getNivelActual();
    private int movimientosFaltantes = partida.getCantMovimientosFaltantes();
    private ArrayList<Objetivo> objetivos = (ArrayList<Objetivo>) partida.getNivelActual().getObjetivos();
    
    
    
    
    
    public int getMovimientosFaltantes() {
		return movimientosFaltantes;
	}
	public void setMovimientosFaltantes(int movimientosFaltantes) {
		this.movimientosFaltantes = movimientosFaltantes;
	}
	public Nivel getNivelActual() {
		return nivelActual;
	}
	public void setNivelActual(Nivel nivelActual) {
		this.nivelActual = nivelActual;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	public Coordenada getCoordenadaActual() {
		return coordenadaActual;
	}
	public void setCoordenadaActual(Coordenada coordenadaActual) {
		this.coordenadaActual = coordenadaActual;
	}
	public Movimiento getMovimientoARealizar() {
		return movimientoARealizar;
	}
	public void setMovimientoARealizar(Movimiento movimientoARealizar) {
		this.movimientoARealizar = movimientoARealizar;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	public void moverCaramelo(){
		
		this.getTablero().moverCaramelo(this.getCoordenadaActual().getFila(), this.getCoordenadaActual().getColumna(), this.getMovimientoARealizar());
		
	}
    
    
}
