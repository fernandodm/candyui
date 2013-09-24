package windows;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

public class ConfiguracionApplication extends Application{
	
	@Override
	protected Window<?> createMainWindow() {
	
		return new ConfiguracionWindow(this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ConfiguracionApplication().start();

	}

}
