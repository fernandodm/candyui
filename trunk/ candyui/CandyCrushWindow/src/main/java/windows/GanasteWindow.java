package windows;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import appModel.PartidaAppModel;

public class GanasteWindow extends SimpleWindow<PartidaAppModel>{

	public GanasteWindow(WindowOwner parent, PartidaAppModel model) {
		super(parent, model);
		
		this.setTitle("GANASTE!");
		this.setTaskDescription("Haz completado todos los objetivos del nivel, hora de pasar al siguiente!");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		// TODO Auto-generated method stub
		Panel panel = new Panel(mainPanel);
        panel.setLayout(new ColumnLayout(3));
	    	    
	    new Label(panel)
	    	.setText("Puntos totales: ")
	    	.bindValueToProperty("puntaje");
	    
	    new Button(panel)
		.setCaption("Proximo nivel")
		.onClick(new MessageSend(this, "pasarAlSiguienteNivel")); 
	}
	
	public void pasarAlSiguienteNivel(){
		this.getModelObject().getPartida().pasarNivel();
		this.openWindow(new PartidaWindow(this));
	}
	
	protected void openWindow(Window<?> window) {
		window.open();
	
	}
}