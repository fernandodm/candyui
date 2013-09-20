package windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Control;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner; 

import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import appModel.MundoAppModel;

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;

@SuppressWarnings("serial")
public class ConfiguracionWindow extends SimpleWindow<MundoAppModel>{

	public ConfiguracionWindow(WindowOwner parent) {
		super(parent, new MundoAppModel());
	
	}
	
	
	/**@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTaskDescription("Crear Nivel");

		
		super.createMainTemplate(mainPanel);

		this.crearTablaNiveles(mainPanel);
		this.botonesParaEditarNivel(mainPanel);
	}*/

	public void botonesParaEditarNivel(Panel mainPanel){
		
		Panel panel = new Panel(mainPanel);
		
		new Button(panel) //
			.setCaption("Editar");
			//.onClick(new MessageSend(this.getModelObject(), "agregarNivel")); 
		
		new Button(panel) //
			.setCaption("Borrar")
			.setWidth(13);
			
		
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTaskDescription("holaaaaaaaaaaa");
		
		this.setTitle("Configurar juego");
		
		Panel panel = new Panel(mainPanel);
		 
	    final Panel editorPanel = panel;
	    
	    editorPanel.setLayout(new ColumnLayout(3));
	    	    
	    new Label(editorPanel)
	    	.setText("Nombre del mundo:");
	    
	    new TextBox(editorPanel)
	    	.setWidth(150)
	    	.bindValueToProperty("mundo.nombre");
	    
	    //Panel para crear el nivel
	    Panel nivelPanel = new Panel(mainPanel);
	    
	    new Label(nivelPanel)
	    	.setText("Niveles:")
	    	.setForeground(Color.BLUE)
	    	.setFontSize(12);
	    
	    //Ingresar la configuacion del nivel
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
		selector.bindValueToProperty("nivelEnConstruccion.dificultad");
		
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
	    
	    Panel objetivosPanel = new Panel(mainPanel);
	    objetivosPanel.setLayout(new ColumnLayout(3));
	    
	    new Label(objetivosPanel)
	    	.setText("Objetivos:")
	    	.setHeigth(100);
	    
	    this.crearTablaObjetivos(objetivosPanel);
	    
	    this.botonesParaEditarObjetivos(objetivosPanel);
	    
	    Panel tablaNiveles = new Panel(mainPanel);
	    tablaNiveles.setLayout(new ColumnLayout(3));
	    
	    new Label(tablaNiveles)
    		.setText("Niveles creados:")
    		.setHeigth(100);
	    
	    this.crearTablaNiveles(tablaNiveles);
	    
	    this.botonesParaEditarNivel(tablaNiveles);

	}
	
	private void botonesParaEditarObjetivos(Panel objetivosPanel) {
		
		Panel panel = new Panel(objetivosPanel);
		
		new Button(panel) //
			.setCaption("Editar");
			//.onClick(new MessageSend(this.getModelObject(), "agregarNivel")); 
	
		new Button(panel) //
			.setCaption("Borrar");
		
		new Button(panel) //
		.setCaption("Agregar");
	}


	public void crearTablaObjetivos(Panel objetivosPanel){
		
		Table<Objetivo> table = new Table<Objetivo>(objetivosPanel, Objetivo.class);
		table.setHeigth(80);
		table.setWidth(200);
		
		//table.bindItemsToProperty("");
		
		Column<Objetivo> numNivel = new Column<Objetivo>(table); //
		numNivel.setTitle("Objetivo")
				.setFixedSize(200);
		
	}
	
	private void crearTablaNiveles(Panel editorPanel) {
		Table<Nivel> table = new Table<Nivel>(editorPanel, Nivel.class);
		table.setHeigth(80);
		table.setWidth(190);
		table.bindItemsToProperty("losNiveles");
		
		
		
		Column<Nivel> numNivel = new Column<Nivel>(table); //
		numNivel.setTitle("Nivel")
				.setFixedSize(50)
				.bindContentsToProperty("nroNivel");
			
		
		Column<Nivel> nombreNivel = new Column<Nivel>(table); //
		nombreNivel.setTitle("Nombre")
				   .setFixedSize(155)
				   .bindContentsToProperty("nombre");
		
		int cantDeNiveles = this.getModelObject().getMundo().getNiveles().size();
		
		for(int i = 0; i < cantDeNiveles; i++){
			nombreNivel.bindContentsToTransformer(new TransformerGetNivel());
			
			
		}
				
	}

	@Override
	protected void addActions(Panel mainPanel) {
		NotNullObservable notNullObservable1 = new NotNullObservable("nivelEnConstruccion.nombre");
		NotNullObservable notNullObservable2 = new NotNullObservable("tablero.alto");
		NotNullObservable notNullObservable3 = new NotNullObservable("tablero.ancho");
		NotNullObservable notNullObservable4 = new NotNullObservable("nivelEnConstruccion.dificultad");
		NotNullObservable notNullObservable5 = new NotNullObservable("mundo.nombre");
		NotNullObservable notNullObservable6 = new NotNullObservable("nivelEnConstruccion.cantidadMovimientos");
		
		Button boton = new Button(mainPanel); 
		
		boton.disableOnError();
		boton.onClick(new MessageSend(this.getModelObject(), "agregarNivel"));
		boton.setCaption("Agregar nivel");
		boton.setBackground(Color.BLACK);
		
		//////////////////
		////BLOQUEOS//////
		boton.bindEnabled(notNullObservable6);
		boton.bindEnabled(notNullObservable5);
		boton.bindEnabled(notNullObservable4);
		boton.bindEnabled(notNullObservable2);
		boton.bindEnabled(notNullObservable3);
		boton.bindEnabled(notNullObservable1);
	    
		 
	}

}
