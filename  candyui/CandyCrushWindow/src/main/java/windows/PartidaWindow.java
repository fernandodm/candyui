package windows;


import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.ControlBuilder;

import appModel.PartidaAppModel;
import Tp.CandyCrush.Caramelo;
import Tp.CandyCrush.Dificultad;
import Tp.CandyCrush.Movimiento;
import Tp.CandyCrush.Nivel;
import Tp.CandyCrush.Objetivo;
import Tp.CandyCrush.Partida;
import Tp.CandyCrush.Tablero;

public class PartidaWindow extends SimpleWindow<PartidaAppModel>{

	public PartidaWindow(WindowOwner parent) {
		super(parent, new PartidaAppModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("CandyCrush");
		this.setTaskDescription("Comenza a jugar");
	
		this.createResultsGrid(mainPanel);
		
		Panel subMainPanel = new Panel(mainPanel);
		subMainPanel.setLayout(new ColumnLayout(2));
		
		Panel searchFormPanel = new Panel(subMainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));

		new Label(searchFormPanel).setText("Fila").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("coordenadaActual.y");
		
		new Label(searchFormPanel).setText("Columna").setForeground(Color.BLUE);
		new TextBox(searchFormPanel).bindValueToProperty("coordenadaActual.x");
		
		Panel movePanel = new Panel(subMainPanel);
		movePanel.setLayout(new ColumnLayout(2));
		
		Selector<Movimiento> selector = new Selector<Movimiento>(movePanel) 
				.allowNull(false);
		selector.<ControlBuilder>bindValueToProperty("movimientoARealizar");
		selector.bindItemsToProperty("movimientos")
			.setAdapter(new PropertyAdapter(Movimiento.class, "nombre"));
		
		new Button(movePanel)
		.setCaption("Mover")
		.onClick(new MessageSend(this, "chequearFinNivel")); 
		
		Panel pointPanel = new Panel(mainPanel);
		pointPanel.setLayout(new ColumnLayout(2));
		
		new Label(pointPanel)
		.setText("PUNTOS:")
		.bindValueToProperty("puntaje");
		
		new Label(pointPanel)
		.setText("Movimientos restantes: ")
		.bindValueToProperty("movimientosFaltantes");
		
		Panel tasksPanel = new Panel(mainPanel);
		tasksPanel.setLayout(new ColumnLayout(3));
		
		new Label(tasksPanel)
		.setText("OBJETIVOS:");
		
		Table<Objetivo> table = new Table<Objetivo>(tasksPanel, Objetivo.class);
		table.setHeigth(80);
		table.setWidth(200);
		table.bindItemsToProperty("objetivos");
		
		Column<Objetivo> nomObjetivo = new Column<Objetivo>(table); 
		nomObjetivo.setTitle("Objetivos")
				.setFixedSize(200)
				.bindContentsToProperty("getDescripcion");
		
		Column<Objetivo> estado = new Column<Objetivo>(table); //
		estado.setTitle("Estado")
				.setFixedSize(50)
				.bindContentsToProperty("seCumplioDescripcion");

		nomObjetivo.bindContentsToTransformer(new TransformerGetObjetivo());
		
	}
	
	public void chequearFinNivel(){
		
		this.getModelObject().moverCaramelo();
		if(this.getModelObject().getNivelActual().isTermino()){
			this.openWindow(new GanasteWindow(this, this.getModelObject())); 
			}
		if(this.getModelObject().getNivelActual().perdio()){
			this.openWindow(new PerdisteWindow(this, this.getModelObject())); 
		}
		
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
		

		this.describeResultsGrid(table);
	}
	
	protected void describeResultsGrid(Table<Caramelo[]> table) {
	
		int ancho = this.getModelObject().getPartida().getNivelActual().getTablero().getAncho();
		
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
	protected void openWindow(Window<?> window) {
		window.open();
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

		// Deshabilitar los botones si no hay ning�n elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("celularSeleccionado");
		remove.bindEnabled(elementSelected);
		edit.bindEnabled(elementSelected);
	}
	*/


	
}
