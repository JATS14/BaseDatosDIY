package BaseDatos;

public class Imagen {

	String id;
	String autor;
	String ano;
	String tamano;
	String datos;
	String descripcion;
	String nombre;

	public Imagen(String id, String nombre, String ano, String autor,String tamano, String descripcion, String datos) {
		this.id = id;
		this.tamano = tamano;
		this.datos = datos;
		this.autor = autor;
		this.ano = ano;
		this.descripcion = descripcion;
		this.nombre = nombre;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPeso() {
		return tamano;
	}
	public void setPeso(String peso) {
		this.tamano = peso;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getDatos() {
		return datos;
	}
	public void setDatos(String datos) {
		this.datos = datos;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String Descripcion) {
		this.descripcion = Descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String Nombre) {
		this.nombre = Nombre;
	}
	
	
	public String toString() {
		String texto = "ID: " + this.id + " / Nombre: " + this.nombre + " / A�o: " + this.ano +" / Autor: " + this.autor + " / Tamano: " + this.tamano + " / Descripcion: "+ this.descripcion+ " / Datos: " + this.datos;
		return texto;
	}

}
