package windows;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import Tp.CandyCrush.Tablero;

public class TableroApplication extends Application {

	@Override
	protected Window<?> createMainWindow() {
		
		return new TableroWindow(this, new Tablero());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new TableroApplication().start();

	}


}
