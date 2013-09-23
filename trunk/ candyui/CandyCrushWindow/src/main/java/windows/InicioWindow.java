package windows;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import appModel.MundoAppModel;


@SuppressWarnings("all")
public class InicioWindow extends SimpleWindow<MundoAppModel>{

	public InicioWindow(WindowOwner parent) {
		super(parent, new MundoAppModel());
		
		this.setTitle("CandyCrush");
		this.setTaskDescription("¿Que desea hacer?");
	
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		
		new Button(actionsPanel)
			.setCaption("Jugar");
		
		new Button(actionsPanel)
			.setCaption("Configurar")
			.onClick(new MessageSend(this, "configurarJuego"));
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		new Panel(mainPanel)
			.setLayout(new ColumnLayout(2));
		
	}
	
	public void configurarJuego() {
		this.openDialog(new ConfiguracionWindow(this, this.getModelObject()));
	}
	
	protected void openDialog(Window<?> window) {
		window.open();
	}

}
