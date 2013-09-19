package windows;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
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

import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Tablero;

public class ConfiguracionWindow extends SimpleWindow<Tablero>{

	public ConfiguracionWindow(WindowOwner parent) {
		super(parent, new Tablero());
	
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Configurar juego");
		
		 Panel panel = new Panel(mainPanel);
		 
	    final Panel editorPanel = panel;
		    
	    editorPanel.setLayout(new ColumnLayout(2));
	    
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
		;
	}
	
	@Override
	protected void addActions(Panel mainPanel) {
		
		new Button(mainPanel) //
			.setCaption("Iniciar")
			.onClick(new MessageSend(this, "iniciar")); 
		 
	}
	
	
	public void iniciar() {
		this.openDialog(new TableroWindow(this, this.getModelObject()));
	}
	
	protected void openDialog(Window<?> dialog) {
		this.getModelObject().iniciar();
		dialog.open();
		
	}

}
