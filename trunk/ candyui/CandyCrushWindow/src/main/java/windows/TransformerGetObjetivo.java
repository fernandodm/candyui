package windows;

import Tp.CandyCrush.Objetivo;
import appModel.MundoAppModel;
import appModel.PartidaAppModel;

import com.uqbar.commons.collections.Transformer;

public class TransformerGetObjetivo implements Transformer<Objetivo, String> {

	private MundoAppModel mundoApp;
	private PartidaAppModel partidaApp;
	

	public TransformerGetObjetivo(MundoAppModel mundoApp) {
		this.mundoApp = mundoApp;
	}

	public TransformerGetObjetivo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String transform(Objetivo objetivo) {

		
		return objetivo.getDescripcion();

	}

}
