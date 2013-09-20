package appModel;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Mundo;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Tablero;

@Observable
public class MundoAppModel {
	private Mundo mundo;
	private Nivel nivelEnConstruccion;
	private List<Dificultad> dificultades = Dificultad.getDificultades();
	private Tablero tablero;
	private List<Nivel> losNiveles;
	
	public MundoAppModel(){
		mundo = new Mundo();
		Nivel n = new Nivel();
		n.setNombre("Mundo de chocolate");
		mundo.agregarNivel(n);
		mundo.agregarNivel(n);
		nivelEnConstruccion = new Nivel();
		tablero = new Tablero();
		nivelEnConstruccion.setTablero(tablero);
		tablero.setNivel(nivelEnConstruccion);
		setLosNiveles(mundo.getNiveles());
	}
	
	
	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public List<Dificultad> getDificultades() {
		return dificultades;
	}

	public void setDificultades(List<Dificultad> dificultades) {
		this.dificultades = dificultades;
	}

	
	public Mundo getMundo() {
		return mundo;
	}
	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}
	public Nivel getNivelEnConstruccion() {
		return nivelEnConstruccion;
	}
	public void setNivelEnConstruccion(Nivel nivelEnConstruccion) {
		this.nivelEnConstruccion = nivelEnConstruccion;
	}


	public List<Nivel> getLosNiveles() {
		return losNiveles;
	}


	public void setLosNiveles(List<Nivel> losNiveles) {
		this.losNiveles = losNiveles;
	}
	
	public void agregarNivel(){
		mundo.agregarNivel(nivelEnConstruccion);
		List<Nivel> n = mundo.getNiveles();
		this.setLosNiveles(null);
		this.setLosNiveles(n);
		nivelEnConstruccion = new Nivel();
	}
}
