package windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner; 

import org.uqbar.lacar.ui.model.ControlBuilder;
import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import appModel.MundoAppModel;

import Tp.CandyCrush.Dificultad;

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
		    
	    editorPanel.setLayout(new ColumnLayout(2));
	    
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
			
		
	    ////////////////
	    
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
	       .setWidth(50)
	       .<ControlBuilder>bindValueToProperty("tablero.alto");
	    
	    new Label(tableroPanel)
	    	.setText("Columnas:");
    
	    new TextBox(tableroPanel)
	    	.setWidth(50)
	    	.<ControlBuilder>bindValueToProperty("tablero.ancho");
	    
	    /**
	    Selector<Dificultad> selector = new Selector<Dificultad>(editorPanel) //
				.allowNull(false);
//		selector.bindValueToProperty("dificultad");
			
		selector.bindValueToProperty("dificultades")
//			.setAdapter(new PropertyAdapter(Dificultad.class, "getNombre"))
		;*/
	}
	
	@Override
	protected void addActions(Panel mainPanel) {
		
	//	new Button(mainPanel) //
		//	.setCaption("Iniciar")
		//	.onClick(new MessageSend(this, "iniciar")); 
		 
	}
	
	/**
	public void iniciar() {
		this.openDialog(new TableroWindow(this, this.getModelObject()));
	}
	
	protected void openDialog(Window<?> dialog) {
		this.getModelObject().iniciar();
		dialog.open();
		
	}*/

}
