package BaseDatos;

public class Imagen {

	String id;
	String autor;
	String ano;
	String tamano;
	String datos;

	public Imagen(String id, String autor, String ano,String tamano, String datos) {
		this.id = id;
		this.tamano = tamano;
		this.datos = datos;
		this.autor = autor;
		this.ano = ano;
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
		this.datos = autor;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.datos = ano;
	}
	public String getDatos() {
		return datos;
	}
	public void setDatos(String datos) {
		this.datos = datos;
	}
	
	
	public String toString() {
		String texto = "ID: " + this.id + " / Autor: " + this.autor + " / Año: " + this.ano + " / Tamano: " + this.tamano + " / Datos: " + this.datos;
		return texto;
	}

}
