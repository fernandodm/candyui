package windows;

import Tp.CandyCrush.Caramelo;

import com.uqbar.commons.collections.Transformer;

public class TransformadorGetCaramelo implements Transformer<Caramelo[],String> {

	private int fila;
    
    public TransformadorGetCaramelo(int i){
            this.fila=i;
    }
    
    

    @Override
    public String transform(Caramelo[] s) {
    
    		return s[fila] == null ? "Sin Caramelo" : s[fila].getColor();
    }
	
}