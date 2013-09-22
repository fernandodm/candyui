package windows;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import appModel.MundoAppModel;

import Tp.CandyCrush.Nivel;

public class EditarNivelWindow extends TransactionalDialog<MundoAppModel>{

	public EditarNivelWindow(WindowOwner owner, MundoAppModel model) {
		super(owner, model);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		((ConfiguracionWindow) this.getOwner()).datosParaEditarNivel(mainPanel);
		System.out.println(this.getModelObject().getNivelSeleccionado().getNombre());
	}
	
	@Override
	public void addActions(Panel mainPanel){
		
		new Button(mainPanel)
		.setCaption("Realizar cambios")
		.onClick(new MessageSend(this, "accept"))
		.onClick(new MessageSend(this.getModelObject(), "realizarCambios"));
		
	}

}
