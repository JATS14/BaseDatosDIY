package BaseDatos;

import java.util.PriorityQueue;
import java.util.TreeMap;
import java.io.*;
import java.util.HashMap; 


/* Huffman coding , decoding */

public class Huffman {
    static final boolean readFromFile = false;
    static final boolean newTextBasedOnOldOne = false;

    static PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    static TreeMap<Character, String> codes = new TreeMap<>();
    static String text = "";
    static String encoded = "";
    static String decoded = "";
    static int ASCII[] = new int[1024];
    HashMap<String, Integer> frecuencia = new HashMap<String, Integer>(); 
    
    public static void leertxt() {
		try {
			File archivo = new File("huffman.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String leido;
			int cont = 1; 
			ASCII = new int[1024];
	        nodes.clear();
	        codes.clear();
	        encoded = "";
            decoded = "";
			System.out.println(" Agregando Nodos....");
			while((leido = br.readLine()) != null) {
				if (cont == 1) {
					text = leido;
					cont = 2;
					continue;
				}try {
				String[] nodo = leido.split("-");
				System.out.println(nodo[0] + " , " + nodo[1] + "  -> Con Frecuencia: " +  nodo[2]);
				agregarNodo(nodo[0], nodo[1]);
				}catch(Exception e) {
					encoded = encoded + leido;
				}
			}
			System.out.println(" Nodos Agregados....");
			System.out.println(" texto Codificado: " + encoded);
			handleNewText(text);
			br.close();
			fr.close();
	}catch(Exception e) {
	}
	}


    private static void agregarNodo(String string, String string2) {
    	Node e = new Node(text.charAt(1),string2);
		nodes.add(e);
	}


	public static void guardarEntxt(String frame) {
    	try {
    		File f = new File("huffman.txt");
    		FileWriter w = new FileWriter(f);
    		BufferedWriter  bw = new BufferedWriter(w);
    		PrintWriter wr = new PrintWriter(bw);
    		handleNewText(frame);
    		decodeText();
    		wr.append(text+"\n");
    		wr.append(encoded+"\n");
    		int[] charFreqs = new int[1024];
            for (char c : text.toCharArray()) {
                charFreqs[c]++;
            }
    		codes.forEach((k, v) -> wr.append(k + "-"+ v +  "-" +charFreqs[k] + "\n"));
    		wr.close();
    		bw.close();
    	}catch(Exception e) {
    		System.out.println("Error al Guardar huffman");
    		return;
    	}
		
	}

	private static boolean handleNewText(String text2) {
        	text = text2;
            ASCII = new int[1024];
            nodes.clear();
            codes.clear();
            encoded = "";
            decoded = "";
            calculateCharIntervals(nodes, false);
            buildTree(nodes);
            generateCodes(nodes.peek(), "");
            encodeText();
            decodeText();
            return false;
    }

    private static void decodeText() {
        decoded = "";
        Node node = nodes.peek();
        for (int i = 0; i < encoded.length(); ) {
            Node tmpNode = node;
            while (tmpNode.left != null && tmpNode.right != null && i < encoded.length()) {
                if (encoded.charAt(i) == '1')
                    tmpNode = tmpNode.right;
                else tmpNode = tmpNode.left;
                i++;
            }
            if (tmpNode != null)
                if (tmpNode.character.length() == 1)
                    decoded += tmpNode.character;
                else
                    System.out.println("Input not Valid");

        }
    }

    private static void encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++)
            encoded += codes.get(text.charAt(i));
    }

    private static void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1)
            vector.add(new Node(vector.poll(), vector.poll()));
    }

    private static void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals) {
    	if (printIntervals) System.out.println("-- intervals --");

        for (int i = 0; i < text.length(); i++)
            ASCII[text.charAt(i)]++;

        for (int i = 0; i < ASCII.length; i++)
            if (ASCII[i] > 0) {
                vector.add(new Node(ASCII[i] / (text.length() * 1.0), ((char) i) + ""));
                if (printIntervals)
                    System.out.println("'" + ((char) i) + "' : " + ASCII[i] / (text.length() * 1.0));
}
    }

    private static void generateCodes(Node node, String s) {
        if (node != null) {
            if (node.right != null)
                generateCodes(node.right, s + "1");

            if (node.left != null)
                generateCodes(node.left, s + "0");

            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }
}

class Node {
    Node left, right;
    double value;
    String character;

    public Node(double value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}