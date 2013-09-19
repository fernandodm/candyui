package windows;

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
	    	.setText("Nombre:");
	    
	    new TextBox(editorPanel)
	    	.bindValueToProperty("mundo.nombre");
	    
	   // Panel nivelPanel = new Panel(mainPanel);
	    
	    //Panel para crear el nivel
	   // final Panel editorPanelNivel = nivelPanel;
	    
	   // editorPanelNivel.setLayout(new ColumnLayout(2));
	    
	  // new Label(editorPanel)
	    	//.setText("Niveles:");
	    
	    new Label(editorPanel)
	    	.setText("Dificultad:");
	    
	    Selector<Dificultad> selector = new Selector<Dificultad>(editorPanel) //
				.allowNull(false);
		selector.bindValueToProperty("nivelEnConstruccion.dificultad");
			
		selector.bindValueToProperty("dificultades")
			.setAdapter(new PropertyAdapter(Dificultad.class, "getNombre"));

	 
	    ////////////////
	  /**  
	    new Label(editorPanel)
	        .setText("Fila:");
	    
	    new TextBox(editorPanel)
	       .setWidth(50)
	       .<ControlBuilder>bindValueToProperty("alto");
	    
	    new Label(editorPanel)
	    	.setText("Columna:");
    
	    new TextBox(editorPanel)
	    	.setWidth(50)
	    	.<ControlBuilder>bindValueToProperty("ancho");
	    
	    
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
