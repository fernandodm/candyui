package windows;

import Tp.CandyCrush.Objetivo;

import com.uqbar.commons.collections.Transformer;

public class TransformerGetObjetivo implements Transformer<Objetivo, String> {



	@Override
	public String transform(Objetivo objetivo) {

		
		return objetivo.getDescripcion();

	}

}
