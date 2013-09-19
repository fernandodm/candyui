package windows;


import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import Tp.CandyCrush.Caramelo;
import Tp.CandyCrush.Tablero;

public class TableroWindow extends SimpleWindow<Tablero>{

	public TableroWindow(WindowOwner owner,Tablero tablero) {
		super(owner, tablero);
		
	}

	@Override
	public void createMainTemplate(Panel mainPanel){
		this.setTitle("Tablero CandyCrush");
		this.setTaskDescription("Comenza a jugar");

		super.createMainTemplate(mainPanel);

		this.createResultsGrid(mainPanel); // tablita
		//this.createGridActions(mainPanel);
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		//Panel searchFormPanel = new Panel(mainPanel);
		//searchFormPanel.setLayout(new ColumnLayout(2));

		//new Label(searchFormPanel).setText("Fila").setForeground(Color.BLUE);
		//new TextBox(searchFormPanel).bindValueToProperty("alto");

		//new Label(searchFormPanel).setText("Columna").setForeground(Color.BLUE);
		//new TextBox(searchFormPanel).bindValueToProperty("ancho");
		
	}
	
	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub
		
	}
	
	protected void createResultsGrid(Panel mainPanel) {
		Table<Caramelo[]> table = new Table<Caramelo[]>(mainPanel, Caramelo[].class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("caramelos");
		//table.bindValueToProperty("celularSeleccionado");

		this.describeResultsGrid(table);
	}
	
	protected void describeResultsGrid(Table<Caramelo[]> table) {
		
		int ancho = this.getModelObject().getAncho();
		
		for(int i = 0; i < ancho; i++){
				
				 new Column<Caramelo[]>(table)
                 //
                 .setTitle(Integer.toString(i)).setFixedSize(90)
                 .bindContentsToTransformer(new TransformadorGetCaramelo(i));
                				 
		}
		
		//new Column<Caramelo>(table) //
		//	.setTitle("1")
		//	.setFixedSize(100);
			//.bindContentsToProperty("ancho");

		//new Column<Caramelo>(table) //
		//	.setTitle("2")
		//	.setFixedSize(100);
		//	.bindContentsToProperty("numero");

		//Column<Caramelo> modeloColumn = new Column<Caramelo>(table);
		//modeloColumn.setTitle("3");
		//modeloColumn.setFixedSize(100);
		//modeloColumn.bindContentsToProperty("modeloCelular");

		//Column<Caramelo> ingresoColumn = new Column<Caramelo>(table);
		//ingresoColumn.setTitle("4");
		//ingresoColumn.setFixedSize(100);
		//ingresoColumn.bindContentsToTransformer(new BooleanToSiNoTransformer());
	}

	/**
	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button edit = new Button(actionsPanel);
		edit.setCaption("Editar");
		edit.onClick(new MessageSend(this, "modificarCelular"));

		Button remove = new Button(actionsPanel);
		remove.setCaption("Borrar");
		remove.onClick(new MessageSend(this.getModelObject(), "eliminarCelularSeleccionado"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("celularSeleccionado");
		remove.bindEnabled(elementSelected);
		edit.bindEnabled(elementSelected);
	}
	*/


	
}
