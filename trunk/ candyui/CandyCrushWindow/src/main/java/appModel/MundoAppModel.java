package appModel;

import java.util.Arrays;
import java.util.List;
import org.uqbar.commons.model.ObservableUtils;
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
		
	private Integer alto;
	private Integer ancho;
	
	private Integer puntaje;
	private Integer cantMovimientos;
	private Dificultad dificultad;
	private String nombre;
	
	private List<Objetivo> objetivos;
	private Objetivo objetivo;
	private ExplosionesPorColor explosionesPorColor = new ExplosionesPorColor();
	private GrandesExplosiones grandesExplosiones = new GrandesExplosiones();
	
	private List<Nivel> losNiveles = mundo.getNiveles();
	private List<Dificultad> dificultades = Dificultad.getDificultades();
	private List<String> colores;
	private List<Integer> selectorExplosiones = Arrays.asList(1,2,3,4,5);
	
	private Nivel nivelSeleccionado; 
	private Objetivo objetivoSeleccionado;
	
	
	//**************//
	// ACCIONES	    //
	//**************//

	public boolean getPuedeAgregar(){
	
		return this.alto != null && this.alto > 1 && this.ancho != null && this.ancho > 1
				&& this.nombre != null && this.dificultad != null 
				&& this.cantMovimientos != null && this.puntaje != null
				&& this.cantMovimientos > 0 && this.puntaje > 0;
		
	}
	
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
	
	/**
	 * Renumera el nivel que se esta construyendo
	 */
	public void renumerarNivelEnConstruccion() {
		if(nivelEnConstruccion.getNroNivel() > nivelSeleccionado.getNroNivel()){
			nivelEnConstruccion.setNroNivel(nivelEnConstruccion.getNroNivel() - 1);
		}
	}

	
	/**
	 * Agrega el nivel en construccion al mundo
	 */
	public void agregarNivel(){
		
		Tablero tablero= new Tablero(alto, ancho, nivelEnConstruccion);
		
		armarNivel(tablero);
				
		mundo.agregarNivel(nivelEnConstruccion);
		
		List<Nivel> niv = mundo.getNiveles();
		this.setLosNiveles(null);
		this.setLosNiveles(niv);
		nivelEnConstruccion = new Nivel();
		tablero = new Tablero();
		nivelEnConstruccion.setTablero(tablero);
		tablero.setNivel(nivelEnConstruccion);
		
	}

	/**
	 * Arma el nivelEnConcstruccion
	 * @param tablero
	 */
	public void armarNivel(Tablero tablero) {
		nivelEnConstruccion.setCantidadMovimientos(cantMovimientos);
		nivelEnConstruccion.setPuntajeMinimo(puntaje);
		nivelEnConstruccion.setDificultad(dificultad);
		nivelEnConstruccion.setNombre(nombre);
		nivelEnConstruccion.setTablero(tablero);
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

	/**
	 * Elimmina el objetivo seleccionado y actualiza la vista
	 */
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
	
			
	}
		
	public void validarBotonAgregar(){
		ObservableUtils.firePropertyChanged(this, "puedeAgregar", this.getPuedeAgregar());
	}
	
	//*******************//
	//Getters and Setters//
	//*******************//
		
		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
			this.validarBotonAgregar(); 
		}

		
		public Dificultad getDificultad() {
			return dificultad;
		}

		public void setDificultad(Dificultad dificultad) {
			this.dificultad = dificultad;
			this.validarBotonAgregar(); 
		}
		
		public Integer getPuntaje() {
			return puntaje;
		}

		public void setPuntaje(Integer puntaje) {
			this.puntaje = puntaje;
			this.validarBotonAgregar(); 
		}

		public Integer getCantMovimientos() {
			return cantMovimientos;
		}

		public void setCantMovimientos(Integer cantMovimientos) {
			this.cantMovimientos = cantMovimientos;
			this.validarBotonAgregar(); 
		}
		
		public Integer getAlto() {
			return alto;
		}

		public void setAlto(Integer alto) {
			this.alto = alto;
			this.validarBotonAgregar(); 
		}

		public Integer getAncho() {
			return ancho;
		}

		public void setAncho(Integer ancho) {
			this.ancho = ancho;
			this.validarBotonAgregar(); 
		}
				
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

}
