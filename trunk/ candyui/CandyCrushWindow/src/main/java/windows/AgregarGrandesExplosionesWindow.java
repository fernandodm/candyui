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

public class AgregarGrandesExplosionesWindow  extends TransactionalDialog<MundoAppModel>{

	public AgregarGrandesExplosionesWindow(WindowOwner owner,
			MundoAppModel model) {
		super(owner, model);
		this.getModelObject().setObjetivo(this.getModelObject().getGrandesExplosiones());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Objetivo: Grandes Explosiones");
		
		Panel panel = new Panel(mainPanel);
		panel.setLayout(new ColumnLayout(2));
		
		List<String> colores = this.getModelObject().getNivelEnConstruccion().getDificultad().getColores();
		
		this.getModelObject().setColores(colores);
		
		new Label(panel)
			.setText("Color:");
		
		Selector<String> selectorColor = new Selector<String>(panel) //
				.allowNull(false);
		selectorColor.bindValueToProperty("grandesExplosiones.color");
		selectorColor.bindItemsToProperty("colores");
		
		new Label(panel)
			.setText("Cantidad:");
		
		Selector<Integer> selectorCantidad = new Selector<Integer>(panel) //
				.allowNull(false);
		selectorCantidad.bindValueToProperty("grandesExplosiones.cantidadGrandesExplosiones");
		selectorCantidad.bindItemsToProperty("selectorExplosiones");

	}
	
	@Override
	public void addActions(Panel mainPanel){
		
		new Button(mainPanel)
			.setCaption("Agregar")	
			.onClick(new MessageSend(this.getModelObject(), "agregarObjetivo"))
			.onClick(new MessageSend(this, "accept"));
			
		new Button(mainPanel)
			.setCaption("Cancelar")
			.onClick(new MessageSend(this, "cancel"));
		
		
	}
		

}
