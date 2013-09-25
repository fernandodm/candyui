package windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ControlBuilder;

import Tp.CandyCrush.Dificultad;
import appModel.MundoAppModel;


public class EditarNivelWindow extends TransactionalDialog<MundoAppModel>{

	public EditarNivelWindow(WindowOwner owner, MundoAppModel model) {
		super(owner, model);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
								
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
    		.<ControlBuilder>bindValueToProperty("nivelSeleccionado.nombre");
	    
	    new Label(datosNivelPanel)
	    	.setText("Dificultad:");
	    
	    Selector<Dificultad> selector = new Selector<Dificultad>(datosNivelPanel) //
				.allowNull(false);
		selector.<ControlBuilder>bindValueToProperty("nivelSeleccionado.dificultad");
		
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
	        .<ControlBuilder>bindValueToProperty("nivelSeleccionado.tablero.alto");
	    
	    new Label(tableroPanel)
	    	.setText("Columnas:");
    
	    new TextBox(tableroPanel)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(30)
	    	.<ControlBuilder>bindValueToProperty("nivelSeleccionado.tablero.ancho");
	    
	    new Label(tableroPanel)
        	.setText("Cantidad de movimientos:");
    
	    new TextBox(tableroPanel)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(30)
    		.<ControlBuilder>bindValueToProperty("nivelSeleccionado.cantidadMovimientos");
	    
	    Panel puntos = new Panel(mainPanel);
	    puntos.setLayout(new ColumnLayout(2));
	    
	    new Label(puntos)
			.setText("Cantidad de Puntos:");
    
	    new TextBox(puntos)
	    	.withFilter(new StringTextFilter())
	    	.setWidth(80)
			.bindValueToProperty("nivelSeleccionado.puntajeMinimo");
	}
	
	@Override
	public void addActions(Panel mainPanel){
		
		new Button(mainPanel)
			.setCaption("Realizar cambios")
			.onClick(new MessageSend(this.getModelObject(), "realizarCambios"))
			.onClick(new MessageSend(this, "accept"));
		

	}

}
