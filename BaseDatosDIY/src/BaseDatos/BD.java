package BaseDatos;

import java.util.ArrayList;

public class BD {
	
	String nombre;
	ArrayList<BDColeccion> colecciones = new ArrayList<BDColeccion>();
	
	
	public BD(String nombre) {
		this.nombre = nombre;
	}
	
	public BDColeccion getColeccion(String nombre) {
		BDColeccion coleccion = new BDColeccion(nombre);
		colecciones.add(coleccion);
		return coleccion;
	}
	
	public void cerrarColecciones() {
		colecciones.get(0).cerrarBD();
		return;
	}
}

