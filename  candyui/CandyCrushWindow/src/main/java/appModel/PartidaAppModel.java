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
import Tp.CandyCrush.Partida;

@Observable
public class PartidaAppModel {
	
	private Partida partida;
	private ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) Arrays.asList(new Arriba(),new Derecha(),new Abajo(), new Izquierda());
    private Coordenada coordenadaActual;
    private Movimiento movimientoARealizar;
    
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
    
    
    
}
