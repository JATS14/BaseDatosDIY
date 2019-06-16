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
			return encontra2;
		}if(coleccion.size() == 1) {
			return coleccion;
		}else {
			System.out.println("Buscando en  \""+ "id" + "\". Elementos con: " + busqueda);
			for (int i = 0; i < coleccion.size();i++) {
				Imagen img = coleccion.get(i);
				String b = busqueda;
				if (img.getId().equals(b)){
					encontra2.add(img);
				}
			}
			if (encontra2.size() == 0) {
				System.out.println("Error en el Query");
			}
		}	
		return encontra2;
	}
	public Imagen buscarB(String busqueda) {
		for (int i = 0; i < coleccion.size();i++) {
			Imagen img = coleccion.get(i);
			if (img.getId().equals(busqueda)){
				return img;
			}
		}busqueda ="\""+ busqueda+"\"";
		for (int i = 0; i < coleccion.size();i++) {
			Imagen img = coleccion.get(i);
			if (img.getId().equals(busqueda)){
				return img;
			}
		}
		
		return null;
	}

	public int tamano() {
		return encontra2.size();
	}
	
	public Imagen get(int index) {
		return encontra2.get(index);
	}

	public void cerrar() {
		encontra2 = new ArrayList<Imagen>();
		atributo = "";
		busqueda = "";
	}
	
	public Imagen encontrarId(String iD) {
		for (int i = 0; i < encontra2.size();i++) {
			Imagen img = encontra2.get(i);
			String b = busqueda;
			if (img.id.equals(b)){
				return img;
			}
	}
	return null;
	}
}
