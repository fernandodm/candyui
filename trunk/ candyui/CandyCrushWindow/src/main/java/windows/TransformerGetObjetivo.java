package windows;

import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;

import com.uqbar.commons.collections.Transformer;

public class TransformerGetObjetivo implements Transformer<Objetivo, String> {

	private MundoAppModel mundoApp;

	public TransformerGetObjetivo(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}

	@Override
	public String transform(Objetivo objetivo) {

		
		return objetivo.getDescripcion();

	}

}
