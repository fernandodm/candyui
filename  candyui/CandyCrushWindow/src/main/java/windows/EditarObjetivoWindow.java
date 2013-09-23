package windows;

import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import Tp.CandyCrush.Objetivo;

public class EditarObjetivoWindow extends TransactionalDialog<Objetivo>{

	public EditarObjetivoWindow(WindowOwner owner, Objetivo model) {
		super(owner, model);
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		
	}

}
