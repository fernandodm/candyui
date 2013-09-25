package windows;

import java.awt.Color;
import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.TextFilter;
import org.uqbar.arena.widgets.TextInputEvent;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner; 
import org.uqbar.lacar.ui.model.ControlBuilder;

import com.uqbar.commons.StringUtils;

import appModel.MundoAppModel;
import appModel.PartidaAppModel;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Partida;

@SuppressWarnings("all")
public class ConfiguracionWindow extends SimpleWindow<MundoAppModel>{

	public ConfiguracionWindow(WindowOwner parent) {
		super(parent, new MundoAppModel());
		
		this.setTitle("Configurar juego");
		this.setTaskDescription("Agregue niveles");
	
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
						
		Panel panel = new Panel(mainPanel);
	    
	    panel.setLayout(new ColumnLayout(3));
	    	    
	    new Label(panel)
	    	.setText("Nombre del mundo:");
	    
	    new TextBox(panel)
	    	.setWidth(150)
	    	.bindValueToProperty("mundo.nombre");
	    
	    this.datosParaEditarNivel(mainPanel);
	    
	    //Mostrar la tabla de los niveles en el mundo
	    Panel tablaNiveles = new Panel(mainPanel);
	    tablaNiveles.setLayout(new ColumnLayout(3));
	    
	    new Label(tablaNiveles)
    		.setText("Niveles creados:")
    		.setHeigth(100);
	    
	    this.crearTablaNiveles(tablaNiveles);
	    
	    this.botonesParaEditarNivel(tablaNiveles);

	}
	
	/**
	 * Crea en la vista todos los campos para poder agregar un nivel
	 * @param mainPanel
	 */
	public void datosParaEditarNivel(Panel mainPanel){
				
	    Panel nivelPanel = new Panel(mainPanel);
	    
	    new Label(nivelPanel)
	    	.setText("Niveles:")
	    	.setForeground(Color.BLUE)
	    	.setFontSize(12);
	    
	    
	    //Ingresar la configuacion del nuevo nivel    
	    Panel datosNivelPanel = new Panel(mainPanel);
	    datosNivelPanel.setLayout(new ColumnLayout(2));
	    
	    new Label(datosNivelPanel)
    		.setText("Nombre del nivel:");
	    		    
	    new TextBox(datosNivelPanel)
	    	.setWidth(150)
    		.bindValueToProperty("nombre");
	    
	    new Label(datosNivelPanel)
	    	.setText("Dificultad:");
	    
	    Selector<Dificultad> selector = new Selector<Dificultad>(datosNivelPanel) //
				.allowNull(false);
		selector.bindValueToProperty("dificultad");
		
		selector.bindItemsToProperty("dificultades") 
			.setAdapter(new PropertyAdapter(Dificultad.class, "nombre"));
			    
		Panel tableroPanel = new Panel(mainPanel);
		tableroPanel.setLayout(new ColumnLayout(5));
		
		new Label(tableroPanel)
        	.setText("Tablero-")
			.setForeground(Color.BLUE)
			.setFontSize(10)
			.setHeigth(20);
		
	    new Label(tableroPanel)
	        .setText("Filas:");
	 
	    new TextBox(tableroPanel)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(30)
	        .bindValueToProperty("alto");
	   
	    new Label(tableroPanel)
	    	.setText("Columnas:");
    
	    new TextBox(tableroPanel)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(30)
	    	.bindValueToProperty("ancho");
	    
	    new Label(tableroPanel)
        	.setText("Cantidad de movimientos:");
    
	    new TextBox(tableroPanel)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(30)
    		.bindValueToProperty("cantMovimientos");
	    
	    Panel puntos = new Panel(mainPanel);
	    puntos.setLayout(new ColumnLayout(2));
	    
	    new Label(puntos)
			.setText("Cantidad de Puntos:");
    
	    new TextBox(puntos)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(80)
			.bindValueToProperty("puntaje");
	    
	    Panel objetivosPanel = new Panel(mainPanel);
	    objetivosPanel.setLayout(new ColumnLayout(3));
	    
	    new Label(objetivosPanel)
	    	.setText("Objetivos:")
	    	.setHeigth(100);
	    
	    //Crear la tabla de los objetivos del nivel
	    this.crearTablaObjetivos(objetivosPanel);
	    
	    this.botonesParaEditarObjetivos(objetivosPanel);
	}
	
	/**
	 * crea la tabla en donde se ven los niveles
	 * @param editorPanel
	 */
	private void crearTablaNiveles(Panel editorPanel) {
		
		Table<Nivel> table = new Table<Nivel>(editorPanel, Nivel.class);
		table.setHeigth(80);
		table.setWidth(190);
		table.bindItemsToProperty("losNiveles");
		table.bindValueToProperty("nivelSeleccionado");
		
		
		
		Column<Nivel> numNivel = new Column<Nivel>(table); 
		numNivel.setTitle("Nivel")
				.setFixedSize(50)
				.bindContentsToProperty("nroNivel");
			
		
		Column<Nivel> nombreNivel = new Column<Nivel>(table); 
		nombreNivel.setTitle("Nombre")
				   .setFixedSize(155)
				   .bindContentsToProperty("nombre");
	
		nombreNivel.bindContentsToTransformer(new TransformerGetNivel());

				
	}
	
	/**
	 * Crea los botones de edicion del nivel
	 * @param mainPanel
	 */
	public void botonesParaEditarNivel(Panel mainPanel){
		
		NotNullObservable notNullObservable = new NotNullObservable("nivelSeleccionado");
		
		Panel panel = new Panel(mainPanel);
		
		new Button(panel) 
			.setCaption("Editar")
			.onClick(new MessageSend(this, "editarNivel"))
			.bindEnabled(notNullObservable); 
		
		new Button(panel) 
			.setCaption("Borrar")
			.onClick(new MessageSend(this.getModelObject(), "eliminarNivelSeleccionado"))
			.setWidth(13)
			.bindEnabled(notNullObservable); 
	}
	
	/**
	 * crea la tabla para ver los objetivos
	 * @param objetivosPanel
	 */
	public void crearTablaObjetivos(Panel objetivosPanel){
		
		Table<Objetivo> table = new Table<Objetivo>(objetivosPanel, Objetivo.class);
		table.setHeigth(80);
		table.setWidth(200);
		table.bindValueToProperty("objetivoSeleccionado");
		table.bindItemsToProperty("objetivos");
		
		Column<Objetivo> nomObjetivo = new Column<Objetivo>(table); //
		nomObjetivo.setTitle("Objetivo")
				.setFixedSize(200);
		
		nomObjetivo.bindContentsToTransformer(new TransformerGetObjetivo());
		
	} 
	
	/**
	 * crea los botones de edicion del objetivo
	 * @param objetivosPanel
	 */
	private void botonesParaEditarObjetivos(Panel objetivosPanel) {
		
		NotNullObservable notNullObservable1 = new NotNullObservable("dificultad");
				
		Panel panel = new Panel(objetivosPanel);
	
		new Button(panel) 
			.setCaption("Borrar")
			.onClick(new MessageSend(this.getModelObject(), "eliminarObjetivo")); 
		
		new Button(panel) 
			.setCaption("Agregar")
			.onClick(new MessageSend(this, "agregarObjetivo"))
			.bindEnabled(notNullObservable1);
	}
	
	
	@Override
	protected void addActions(Panel mainPanel) {
		
				
		Button boton = new Button(mainPanel); 
		
		boton.disableOnError();
		boton.onClick(new MessageSend(this.getModelObject(), "agregarNivel"));
		boton.setCaption("Agregar nivel");
		boton.setBackground(Color.BLACK);		
		boton.bindEnabledToProperty("puedeAgregar");
	
		new Button(mainPanel)
			.setCaption("Jugar")
			.onClick(new MessageSend(this, "comenzar"));
	    		 
	}
	
	public void comenzar(){
		Partida partida = new Partida(this.getModelObject().getMundo());
        PartidaAppModel app = new PartidaAppModel();
        app.setPartida(partida);
		this.openWindow(new TableroWindow(this, app));
		
	}

	public void agregarObjetivo(){
		this.openWindow(new AgregarObjetivoWindow(this, this.getModelObject()));
	}
	
	public void editarNivel(){
		this.openWindow(new EditarNivelWindow(this, this.getModelObject()));
	}
		
	protected void openWindow(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "realizarCambios"));
		dialog.open();
	}
}
