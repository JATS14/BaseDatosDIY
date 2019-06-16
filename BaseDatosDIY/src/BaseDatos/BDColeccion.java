package BaseDatos;

import java.util.ArrayList;
import java.io.*;

public class BDColeccion {
	
	String nombre;
	BDCursor cursor;
	ArrayList<Imagen> coleccion = new ArrayList<Imagen>();
	
	public BDColeccion(String nombre) {
		this.nombre = nombre;
		try {
			File archivo = new File("BD-DIY.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String leido;
			leido = br.readLine();
			System.out.println("se leyo: " + leido);
			br.close();
			fr.close();
			
			if(leido != "") {
				coleccion = deserializador(leido);
			}
			
	}catch(Exception e) {
		System.out.println("Error al Leer base de datos...");
		System.out.println("Se creara una nueva Base de datos");
		coleccion = new ArrayList<Imagen>();
	}
	
		
		System.out.println("");
	}

	public void insert(Imagen dbObjectLibro) {
		coleccion.add(dbObjectLibro);
	}

	public int getCount() {
		int c = coleccion.size();
		return c;
	}
	
	public BDCursor find() {
		cursor = new BDCursor(coleccion);
		cursor.buscar();
		return cursor;
	}
	
	public Imagen find(String atributo, String busqueda) {
		cursor = new BDCursor(coleccion);
		Imagen img = cursor.buscarB(busqueda);
		if(img == null) {
			return new Imagen(busqueda,"error","error","error","error","error","error");
		}
		return img;
	}
	

	public void actualizar(Imagen busqueda, Imagen actualizar) {
		if (busqueda == null){
			System.out.println("El elemento buscado no existe");
			return;
		}
		if(actualizar.getId() == "") {actualizar.setId(busqueda.getId());}
		if(actualizar.getNombre() == "") {actualizar.setNombre(busqueda.getNombre());}
		if(actualizar.getAno() == "") {actualizar.setAno(busqueda.getAno());}
		if(actualizar.getAutor() == "") {actualizar.setAutor(busqueda.getAutor());}
		if(actualizar.getPeso() == "") {actualizar.setPeso(busqueda.getPeso());}
		if(actualizar.getDescripcion() == "") {actualizar.setDescripcion(busqueda.getDescripcion());}
		if(actualizar.getDatos() == "") {actualizar.setDatos(busqueda.getDatos());}

		
		int posicion = posicion(busqueda.getId());
		coleccion.remove(posicion);
		coleccion.add(actualizar);
		return;
	}

	public Imagen buscar(String atributo, String busqueda) {
		Imagen h = find(atributo, busqueda);
		return h;
	}
	public int posicion(String id) {
		for(int i = 0; i < coleccion.size();i++) {
			Imagen img = coleccion.get(i);
			if (img.id.equals(id)){
				return i;
			}
		}
		return 0;
	}

	public void encontrarYBorrar(Imagen buscaryborrar) {
		if (buscaryborrar == null) {
			System.out.println("Error al borrar elemento");
			return ;
		}
		int posicion = posicion(buscaryborrar.getId());
		coleccion.remove(posicion);
	}

	private ArrayList<Imagen> deserializador(String json) {
		ArrayList<Imagen> coleccion2 = new ArrayList<Imagen>();
		String id = ""; String pe = "";String dato = "";
		String ano = ""; String autor = ""; String des = "";
		String nom = "";
		String[] accion = json.split(";");
		int cont = 1;
		for(int i = 0; i < accion.length; i++) {
			String r = accion[i];
			String[] imagen = r.split(",");
			for(int j = 0; j < imagen.length;j++ ){
				String h = imagen[j];
				String[] parte = h.split(" ");
				if(cont == 1){id = parte[1];}
				if(cont == 2){nom = parte[1];}
				if(cont == 3){autor = parte[1];}
				if(cont == 4){ano = parte[1];}
				if(cont == 5){pe = parte[1];}
				if(cont == 6){des = parte[1];}
				if(cont == 7){dato = parte[1];}
				cont++;
				}
			Imagen g = new Imagen(id,nom,ano,autor,pe,des,dato);
			coleccion2.add(g);
			cont = 1;
		}
		return coleccion2;
	}
	public void cerrarBD() {
	try {
		File f = new File("BD-DIY.txt");
		FileWriter w = new FileWriter(f);
		BufferedWriter  bw = new BufferedWriter(w);
		PrintWriter wr = new PrintWriter(bw);

		wr.write(serializador(coleccion));
		wr.close();
		bw.close();
	}catch(Exception e) {
		System.out.println("Error al Guardar base de datos");
		return;
	}
	}
	
	
	private String serializador(ArrayList<Imagen> list) {
		String TodasImagenes = "";
		ArrayList<Imagen> lista1 = list;
		for ( int i = 0; i < (lista1.size()-1); i++) {
			TodasImagenes = TodasImagenes + serializarImagen(lista1.get(i))+ ";" ;
		}
		TodasImagenes = TodasImagenes + serializarImagen(lista1.get(lista1.size()-1));		
		return TodasImagenes;
	}
	
	private String serializarImagen(Imagen img) {
		String string = 
				"{\"id\": "  + img.getId() + "," +
				"\"Nombre\": " + img.getNombre() + "," +
				"\"a�o\": " + img.getAno() + "," +
				"\"autor\": " + img.getAutor() + "," +
				"\"tama�o\": " + img.getPeso() + "," +
				"\"Descripcion\": " + img.getDescripcion() + "," +
				"\"datos\": " + img.getDatos() + " }";
		return string;
	}

}
