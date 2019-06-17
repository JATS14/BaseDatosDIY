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
	Imagen img1 = new Imagen("001","FotoDeGatitos", "2011","Juan2", "10","imagen1", "0100110110");
	Imagen img2 = new Imagen("002","FotoDeGatitos", "2011" ,"pedro2","10","imagen2", "1001001101");
	Imagen img3 = new Imagen("004", "FotoDeGatitos", "2011","Isak2","10","imagen3", "0100111100");
	Imagen img4 = new Imagen("005","FotoDeGatitos", "2011","Lamadre2", "10", "imagen4", "1010101001");
	coleccion.insert(img1);
	coleccion.insert(img2);
	coleccion.insert(img3);
	coleccion.insert(img4);
	
	//Cuantos Imagenes hay?
	int numDocumentos = (int) coleccion.getCount();
	System.out.println("N�mero de documentos en la colecci�n galeria: " + numDocumentos + "\n");
	
	
	//Buscar Todos los elementos en la BD
	BDCursor cursor = coleccion.find();
	System.out.println("Todos los datos disponibles: ");
	for (int i = 0; i < cursor.tamano();i++) {
		System.out.println(cursor.get(i).toString());
	}
	cursor.cerrar();
	
	//Buscar Solo dato con id 001
	Imagen img = coleccion.find("id","001");
	System.out.println("Imagen: " + img.toString() );
	
	//Actualizar solo un elemento
	Imagen buscar = coleccion.buscar("id","001");
	Imagen actualizar = new Imagen("","","","","","","0001111101");
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
	
	Imagen buscaryborrar = coleccion.buscar("id","001");
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
