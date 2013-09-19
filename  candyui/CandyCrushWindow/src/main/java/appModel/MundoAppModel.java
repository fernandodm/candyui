package appModel;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Mundo;
import Tp.CandyCrush.Nivel;

@Observable
public class MundoAppModel {
	private Mundo mundo;
	private Nivel nivelEnConstruccion;
	private List<Dificultad> dificultades = Dificultad.getDificultades();
	
	public List<Dificultad> getDificultades() {
		return dificultades;
	}

	public void setDificultades(List<Dificultad> dificultades) {
		this.dificultades = dificultades;
	}

	public MundoAppModel(){
		mundo = new Mundo();
		nivelEnConstruccion = new Nivel();
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
}
