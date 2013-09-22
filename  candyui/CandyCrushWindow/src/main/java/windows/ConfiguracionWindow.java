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
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner; 

import org.uqbar.lacar.ui.model.ControlBuilder;

import appModel.MundoAppModel;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

@SuppressWarnings("serial")
public class ConfiguracionWindow extends SimpleWindow<MundoAppModel>{

	public ConfiguracionWindow(WindowOwner parent) {
		super(parent, new MundoAppModel());
	
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Configurar juego");
		
		Panel panel = new Panel(mainPanel);
		 
	    final Panel editorPanel = panel;
	    
	    editorPanel.setLayout(new ColumnLayout(3));
	    	    
	    new Label(editorPanel)
	    	.setText("Nombre del mundo:");
	    
	    new TextBox(editorPanel)
	    	.setWidth(150)
	    	.bindValueToProperty("mundo.nombre");
	    
	    this.datosParaEditarNivel(mainPanel);
	    
	    Panel tablaNiveles = new Panel(mainPanel);
	    tablaNiveles.setLayout(new ColumnLayout(3));
	    
	    new Label(tablaNiveles)
    		.setText("Niveles creados:")
    		.setHeigth(100);
	    
	    this.crearTablaNiveles(tablaNiveles);
	    
	    this.botonesParaEditarNivel(tablaNiveles);

	}
	
	public void datosParaEditarNivel(Panel mainPanel){
				
	    Panel nivelPanel = new Panel(mainPanel);
	    
	    new Label(nivelPanel)
	    	.setText("Niveles:")
	    	.setForeground(Color.BLUE)
	    	.setFontSize(12);
	    
	    /////////////////////////////////////////////
	    //Ingresar la configuacion del nuevo nivel
	    ////////////////////////////////////////////
	    
	    Panel datosNivelPanel = new Panel(mainPanel);
	    datosNivelPanel.setLayout(new ColumnLayout(2));
	    
	    new Label(datosNivelPanel)
    		.setText("Nombre del nivel:");
	    		    
	    new TextBox(datosNivelPanel)
	    	.setWidth(150)
    		.bindValueToProperty("nivelEnConstruccion.nombre");
	    
	    new Label(datosNivelPanel)
	    	.setText("Dificultad:");
	    
	    Selector<Dificultad> selector = new Selector<Dificultad>(datosNivelPanel) //
				.allowNull(false);
		selector.<ControlBuilder>bindValueToProperty("nivelEnConstruccion.dificultad");
		
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
	    	.setWidth(30)
	        .<ControlBuilder>bindValueToProperty("tablero.alto");
	    
	    new Label(tableroPanel)
	    	.setText("Columnas:");
    
	    new TextBox(tableroPanel)
	    	.setWidth(30)
	    	.<ControlBuilder>bindValueToProperty("tablero.ancho");
	    
	    new Label(tableroPanel)
        	.setText("Cantidad de movimientos:");
    
	    new TextBox(tableroPanel)
	    	.setWidth(30)
    		.<ControlBuilder>bindValueToProperty("nivelEnConstruccion.cantidadMovimientos");
	    
	    Panel puntos = new Panel(mainPanel);
	    puntos.setLayout(new ColumnLayout(2));
	    
	    new Label(puntos)
			.setText("Cantidad de Puntos:");
    
	    new TextBox(puntos)
	    	.setWidth(80)
			.<ControlBuilder>bindValueToProperty("nivelEnConstruccion.puntajeMinimo");
	    
	    Panel objetivosPanel = new Panel(mainPanel);
	    objetivosPanel.setLayout(new ColumnLayout(3));
	    
	    new Label(objetivosPanel)
	    	.setText("Objetivos:")
	    	.setHeigth(100);
	    
	    this.crearTablaObjetivos(objetivosPanel);
	    
	    this.botonesParaEditarObjetivos(objetivosPanel);
	}
		
	public void botonesParaEditarNivel(Panel mainPanel){
		
		Panel panel = new Panel(mainPanel);
		
		new Button(panel) //
			.setCaption("Editar")
			.onClick(new MessageSend(this, "editarNivel")); 
		
		new Button(panel) //
			.setCaption("Borrar")
			.onClick(new MessageSend(this.getModelObject(), "eliminarNivelSeleccionado"))
			.setWidth(13);	
	}
	
	private void botonesParaEditarObjetivos(Panel objetivosPanel) {
		
		NotNullObservable notNullObservable = new NotNullObservable("nivelEnConstruccion.dificultad");
		
		Panel panel = new Panel(objetivosPanel);
		
		new Button(panel) //
			.setCaption("Editar");
			//.onClick(new MessageSend(this.getModelObject(), "agregarNivel")); 
	
		new Button(panel) //
			.setCaption("Borrar");
			//.onClick(new MessageSend(this.getModelObject(), "eliminarObjetivo")); 
		
		new Button(panel) //
			.setCaption("Agregar")
			.onClick(new MessageSend(this, "agregarObjetivo"))
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
		
		table.bindItemsToProperty("objetivos");
		
		Column<Objetivo> nomObjetivo = new Column<Objetivo>(table); //
		nomObjetivo.setTitle("Objetivo")
				.setFixedSize(200);
		
		nomObjetivo.bindContentsToTransformer(new TransformerGetObjetivo(this.getModelObject()));
		
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
		
		
		
		Column<Nivel> numNivel = new Column<Nivel>(table); //
		numNivel.setTitle("Nivel")
				.setFixedSize(50)
				.bindContentsToProperty("nroNivel");
			
		
		Column<Nivel> nombreNivel = new Column<Nivel>(table); //
		nombreNivel.setTitle("Nombre")
				   .setFixedSize(155)
				   .bindContentsToProperty("nombre");
	
		nombreNivel.bindContentsToTransformer(new TransformerGetNivel());

				
	}
	
	@Override
	protected void addActions(Panel mainPanel) {
		NotNullObservable notNullObservable1 = new NotNullObservable("nivelEnConstruccion.nombre");
				
		Button boton = new Button(mainPanel); 
		
		boton.disableOnError();
		boton.onClick(new MessageSend(this.getModelObject(), "agregarNivel"));
		boton.setCaption("Agregar nivel");
		boton.setBackground(Color.BLACK);
		
		//////////////////
		////BLOQUEO//////
		/////////////////
		boton.bindEnabled(notNullObservable1);
	    		 
	}

	public void agregarObjetivo(){
		this.openDialog(new AgregarObjetivoWindow(this, this.getModelObject()));
	}
	
	public void editarNivel(){
		this.openDialog(new EditarNivelWindow(this, this.getModelObject()));
	}
	
	protected void openDialog(Dialog<?> dialog) {
		//dialog.onAccept(new MessageSend(this.getModelObject(), "editarNivel"));
		dialog.open();
	}
}
