package windows;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class InicioApplication extends Application{
	
	@Override
	protected Window<?> createMainWindow() {
	
		return new InicioWindow(this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new InicioApplication().start();

	}

}
