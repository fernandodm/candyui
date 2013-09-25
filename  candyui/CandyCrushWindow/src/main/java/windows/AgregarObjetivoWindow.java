package windows;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import appModel.MundoAppModel;

public class AgregarObjetivoWindow extends TransactionalDialog<MundoAppModel>{

	public AgregarObjetivoWindow(WindowOwner owner, MundoAppModel model) {
		super(owner, model);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Agregar Objetivo");
		
		new Label(mainPanel)
			.setText("¿Que objetivo desea agregar?");
		
		
	}
	
	@Override
	public void addActions(Panel mainPanel){
		
		Panel panel = new Panel(mainPanel);
		panel.setLayout(new ColumnLayout(2));
		
		new Button(panel)
			.setCaption("Explosiones por color")
			.onClick(new MessageSend(this, "agregarExplosionesPorColor"))
			.setBackground(Color.BLACK);
			
		
		new Button(panel)
			.setCaption("Grandes explosiones")
			.onClick(new MessageSend(this, "agregarGrandesExplosiones"))
			.setBackground(Color.BLACK);
		
	}
	
	public void agregarExplosionesPorColor(){
		this.openWindow(new AgregarExplosionesPorColorWindow(this, this.getModelObject()));
		
	}

	public void agregarGrandesExplosiones(){
		this.openWindow(new AgregarGrandesExplosionesWindow(this, this.getModelObject()));
	}
	
	protected void openWindow(Dialog<?> dialog) {
		dialog.onAccept(new MessageSend(this.getModelObject(), "agregarObjetivo"));
		dialog.open();
		
	}
}
