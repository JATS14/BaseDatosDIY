package BaseDatos;

public class Main {

	public static void main(String[] args) {
		
	//Conectamos con la base de Datos	
	BDCliente cliente = new BDCliente("localhost", 27017);
	
	//Creamos una base de datos
	BD bd = cliente.getBD("Geleria");
	
	//Creamos una coleccion
	BDColeccion coleccion = bd.getColeccion("Imagenes");
	
	//Insertamos elementos
	Imagen img1 = new Imagen("001","Juan", "2011", "10", "0100110110");
	Imagen img2 = new Imagen("002", "pedro", "20414","10", "1001001101");
	Imagen img3 = new Imagen("004", "Isak", "201454541","10", "0100111100");
	Imagen img4 = new Imagen("005","Lamadre", "454", "10", "1010101001");
	coleccion.insert(img1);
	coleccion.insert(img2);
	coleccion.insert(img3);
	coleccion.insert(img4);
	
	//Cuantos Imagenes hay?
	int numDocumentos = (int) coleccion.getCount();
	System.out.println("Número de documentos en la colección galeria: " + numDocumentos + "\n");
	
	
	//Buscar Todos los elementos en la BD
	BDCursor cursor = coleccion.find();
	System.out.println("Todos los datos disponibles: ");
	for (int i = 0; i < cursor.tamano();i++) {
		System.out.println(cursor.get(i).toString());
	}
	cursor.cerrar();
	
	//Buscar Solo dato con id 001
	cursor = coleccion.find("id","001");
	for (int i = 0; i < cursor.tamano();i++) {
		System.out.println(cursor.get(i).toString());
	}
	cursor.cerrar();
	
	//Actualizar solo un elemento
	Imagen buscar = coleccion.buscar("id","002");
	Imagen actualizar = new Imagen("","","","","0001111101");
	coleccion.actualizar(buscar,actualizar);
	
	
	System.out.println("\n");
	System.out.println("galeria despues de actualizar un elemento: ");
	BDCursor cursor2 = coleccion.find();
	System.out.println("Todos los datos disponibles: ");
	for (int i = 0; i < cursor2.tamano();i++) {
		System.out.println(cursor2.get(i).toString());
	}
	cursor2.cerrar();
		
	//Borar un elemento
	
	Imagen buscaryborrar = coleccion.buscar("id","002");
	coleccion.encontrarYBorrar(buscaryborrar);
	
	System.out.println("\n");
	System.out.println("galeria despues de borrar un elemento: ");
	BDCursor cursor3 = coleccion.find();
	System.out.println("Todos los datos disponibles: ");
	for (int i = 0; i < cursor3.tamano();i++) {
		System.out.println(cursor3.get(i).toString());
	}
	cursor3.cerrar();
	
	//cerrar la coneccion
	cliente.cerrar();
	}
}
