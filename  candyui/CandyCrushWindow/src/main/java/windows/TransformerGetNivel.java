package windows;


import Tp.CandyCrush.Nivel;

import com.uqbar.commons.collections.Transformer;

public class TransformerGetNivel implements Transformer<Nivel,String> {
    

    @Override
    public String transform(Nivel n) {
    		
    		return n.getNombre();
    }

}
