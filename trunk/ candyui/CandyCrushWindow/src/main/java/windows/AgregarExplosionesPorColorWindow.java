package windows;

import java.util.List;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ControlBuilder;

import appModel.MundoAppModel;

public class AgregarExplosionesPorColorWindow extends TransactionalDialog<MundoAppModel>{

	public AgregarExplosionesPorColorWindow(WindowOwner owner,
			MundoAppModel model) {
		super(owner, model);
		this.getModelObject().setObjetivo(this.getModelObject().getExplosionesPorColor());

	}

	
	@Override
	protected void createFormPanel(Panel mainPanel) {

		this.setTitle("Objetivo: Explosiones por color");
		
		Panel panel = new Panel(mainPanel);
		panel.setLayout(new ColumnLayout(2));
		
		List<String> colores = this.getModelObject().getDificultad().getColores();
		
		this.getModelObject().setColores(colores);
		
		new Label(panel)
			.setText("Color:");
		
		Selector<String> selector = new Selector<String>(panel) 
				.allowNull(false);
		selector.bindValueToProperty("explosionesPorColor.color");
		
		selector.bindItemsToProperty("colores");
		
		new Label(panel)
			.setText("Cantidad:");
		
		new TextBox(panel)
			.withFilter(new StringTextFilter())
			.setWidth(50)
			.<ControlBuilder>bindValueToProperty("explosionesPorColor.cantidad");
	
		
	}
	
	@Override
	public void addActions(Panel mainPanel){
		
		new Button(mainPanel)
			.setCaption("Agregar")	
			.onClick(new MessageSend(this.getModelObject(), "agregarObjetivo"))
			.onClick(new MessageSend(this, "accept"))
			.bindEnabledToProperty("explosionesPorColor.sePuedeAgregar");
		
		new Button(mainPanel)
			.setCaption("Cancelar")
			.onClick(new MessageSend(this, "cancel"));
		
		
	}

}
