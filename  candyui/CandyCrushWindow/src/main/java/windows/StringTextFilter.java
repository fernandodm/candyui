package windows;

import org.uqbar.arena.widgets.TextFilter;
import org.uqbar.arena.widgets.TextInputEvent;

import com.uqbar.commons.StringUtils;

public class StringTextFilter implements TextFilter {

	@Override
	
		public boolean accept(TextInputEvent event) {

			
            return StringUtils.isNumeric(event.getPotentialTextResult()) 
            		;

    }
}


