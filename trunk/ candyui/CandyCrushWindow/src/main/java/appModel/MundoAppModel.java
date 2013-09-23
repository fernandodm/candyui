package appModel;

import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.ExplosionesPorColor;
import Tp.CandyCrush.GeneradorNroNivel;
import Tp.CandyCrush.GrandesExplosiones;
import Tp.CandyCrush.Mundo;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Tablero;

@Observable
public class MundoAppModel {
	private Mundo mundo = new Mundo();
	private Nivel nivelEnConstruccion = new Nivel();
	private List<Dificultad> dificultades = Dificultad.getDificultades();
	private Tablero tablero = new Tablero();
	private List<Nivel> losNiveles = mundo.getNiveles();
	private Nivel nivelSeleccionado;
	private List<String> colores;
	private Objetivo objetivo;
	private ExplosionesPorColor explosionesPorColor = new ExplosionesPorColor();
	private GrandesExplosiones grandesExplosiones = new GrandesExplosiones();
	private List<Integer> selectorExplosiones = Arrays.asList(1,2,3,4);
	private List<Objetivo> objetivos;
	private Objetivo objetivoSeleccionado;
	private boolean puedeAgregar;
	
	public boolean isPuedeAgregar() {
		return puedeAgregar;
	}

	public void setPuedeAgregar(boolean puedeAgregar) {
		this.puedeAgregar = puedeAgregar;
	}

	public MundoAppModel(){
		
		nivelEnConstruccion.setTablero(tablero);
		tablero.setNivel(nivelEnConstruccion);
	}
	
	//*******************//
	//Getters and Setters//
	//*******************//
	
	public ExplosionesPorColor getExplosionesPorColor() {
		return explosionesPorColor;
	}
	public List<Integer> getSelectorExplosiones() {
		return selectorExplosiones;
	}
	public void setSelectorExplosiones(List<Integer> selectorExplosiones) {
		this.selectorExplosiones = selectorExplosiones;
	}
	public GrandesExplosiones getGrandesExplosiones() {
		return grandesExplosiones;
	}
	public void setGrandesExplosiones(GrandesExplosiones grandesExplosiones) {
		this.grandesExplosiones = grandesExplosiones;
	}
	public void setExplosionesPorColor(ExplosionesPorColor explosionesPorColor) {
		this.explosionesPorColor = explosionesPorColor;
	}
	public Objetivo getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
	public Nivel getNivelSeleccionado() {
		return nivelSeleccionado;
	}
	public void setNivelSeleccionado(Nivel nivelSeleccionado) {
		this.nivelSeleccionado = nivelSeleccionado;
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
	public List<String> getColores() {
		return colores;
	}
	public void setColores(List<String> colores) {
		this.colores = colores;
	}
	public List<Objetivo> getObjetivos() {
		return objetivos;
	}
	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}
	public Objetivo getObjetivoSeleccionado() {
		return objetivoSeleccionado;
	}
	public void setObjetivoSeleccionado(Objetivo objetivoSeleccionado) {
		this.objetivoSeleccionado = objetivoSeleccionado;
	}
	
	//**************//
	// ACCIONES	    //
	//**************//
	
	/**public boolean puedeAgregarNivel(){
		
		return tablero.getAlto() != 0 && tablero.getAncho() != 0
				&& nivelEnConstruccion.getNombre() != null &&
				nivelEnConstruccion.getCantidadMovimientos() != 0
				&& nivelEnConstruccion.getDificultad() != null &&
				(nivelEnConstruccion.getPuntajeMinimo() != 0 || 
				!nivelEnConstruccion.getObjetivos().isEmpty());
	}*/
	
	/**
	 * Elimina el nivel seleccionado
	 */
	public void eliminarNivelSeleccionado(){
				
		mundo.eliminarNivel(nivelSeleccionado);
		
		renumerarNiveles();
				
		List<Nivel> niv = mundo.getNiveles();
		this.setLosNiveles(null);
		this.setLosNiveles(niv);
			
	}

	/**
	 * Una vez que se elimino un nivel se renumeran los niveles
	 */
	public void renumerarNiveles() {
		
		for(Nivel n : mundo.getNiveles()){
			if(n.getNroNivel() > nivelSeleccionado.getNroNivel()){
				n.setNroNivel(n.getNroNivel() - 1);
			}
		}
		renumerarNivelEnConstruccion();
		GeneradorNroNivel.restarNroNivel();
	}

	public void renumerarNivelEnConstruccion() {
		if(nivelEnConstruccion.getNroNivel() > nivelSeleccionado.getNroNivel()){
			nivelEnConstruccion.setNroNivel(nivelEnConstruccion.getNroNivel() - 1);
		}
	}
	
	/**
	 * Agrega el nivel en construccion al mundo
	 */
	public void agregarNivel(){
		
		mundo.agregarNivel(nivelEnConstruccion);
		
		List<Nivel> niv = mundo.getNiveles();
		this.setLosNiveles(null);
		this.setLosNiveles(niv);
		nivelEnConstruccion = new Nivel();
		
	}

	/**
	 * Agrega un objetivo (grandes explosiones, explosiones por color)
	 * al nivel en construccion
	 */
	public void agregarObjetivo(){
				
		nivelEnConstruccion.agregarObjetivo(objetivo);
		
		List<Objetivo> objetivos = nivelEnConstruccion.getObjetivos();

		this.setObjetivos(null);
		this.setObjetivos(objetivos);
		explosionesPorColor = new ExplosionesPorColor();
		grandesExplosiones = new GrandesExplosiones();
		
	}

	public void eliminarObjetivo(){
				
		nivelEnConstruccion.eliminarObjetivo(objetivoSeleccionado);
				
		List<Objetivo> objs = nivelEnConstruccion.getObjetivos();
		this.setObjetivos(null);
		this.setObjetivos(objs);
			
		
	}
	
	public void realizarCambios(){

		List<Nivel> niv = mundo.getNiveles();
		this.setLosNiveles(null);
		this.setLosNiveles(niv);
		nivelSeleccionado = null;
		
	}

}
