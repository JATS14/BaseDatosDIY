package BaseDatos;


public class BDCliente {

	String host;
	int port;
	BD bd;
	
	public BDCliente(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public BD getBD(String nombre) {
		this.bd = new BD(nombre);
		return bd;
	}
	
	public void cerrar() {
		bd.cerrarColecciones();
		System.out.println("Coneccion Cerrada");
	}
	
}
