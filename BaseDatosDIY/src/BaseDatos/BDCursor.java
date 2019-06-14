package BaseDatos;

import java.util.ArrayList;

public class BDCursor {
	
	private ArrayList<Imagen> coleccion = new ArrayList<Imagen>();
	private ArrayList<Imagen> encontra2 = new ArrayList<Imagen>();
	private String atributo;
	private String busqueda;
	
	public BDCursor(ArrayList<Imagen> coleccion) {
		this.coleccion = coleccion;
		this.atributo = "";
		this.busqueda = "";
	}
	
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	
	public ArrayList<Imagen> buscar(){
		if (atributo == "" && busqueda == "") {
			encontra2 = coleccion;
		}else {
			System.out.println("Buscando en  \""+ atributo + "\". Elementos con: " + busqueda);
			for (int i = 0; i < coleccion.size();i++) {
				Imagen img = coleccion.get(i);
				String b = busqueda;
				if (img.id.equals(b) || img.tamano.equals(b) || img.datos.equals(b)){
					encontra2.add(img);
				}
			}
			if (encontra2.size() == 0) {
				System.out.println("Error en el Query");
			}
		}	
		return encontra2;
	}

	public int tamano() {
		return encontra2.size();
	}
	
	public Imagen get(int index) {
		return encontra2.get(index);
	}

	public void cerrar() {
		encontra2 = new ArrayList<Imagen>();
	}
	
	public Imagen encontrarId(String iD) {
		for (int i = 0; i < encontra2.size();i++) {
			Imagen img = encontra2.get(i);
			String b = busqueda;
			if (img.id.equals(b) || img.tamano.equals(b) || img.datos.equals(b)){
				return img;
			}
	}
	return null;
	}
}
