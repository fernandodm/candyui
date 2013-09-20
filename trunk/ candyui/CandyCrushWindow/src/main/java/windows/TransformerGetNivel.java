package windows;


import Tp.CandyCrush.Nivel;

import com.uqbar.commons.collections.Transformer;

public class TransformerGetNivel implements Transformer<Nivel,String> {

	private int fila;
    
   /** public TransformerGetNivel(int i){
            this.fila=i;
    }*/
    
    

    @Override
    public String transform(Nivel n) {
    
    		return n.getNombre();
    }

}
