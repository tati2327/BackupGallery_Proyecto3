package com.dataBase;

import java.io.FileWriter;
import java.io.IOException;

import java.util.PriorityQueue;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dataBase.Huffman;
import com.dataBase.HuffmanNode;
import com.dataBase.MyComparator;
import com.structures.SimpleList;

/*
 * Base no SQL
 * Tiene como variables Coded que es un string que contiene una compresion Huffman
 * Huff que es la tabla de compresion de Huffman
 * Base que es una matriz que es basicamente donde se almacenan los datos de la base
 * y ID que es un entero que aumentara cada vez que se asigna una nueva imagen en la base
 * y lo relaciona con la imagen agregada
 */

public class BaseNoSql {

	public String Coded = "";
	public SimpleList<String> Huff = new SimpleList<String>();

	public SimpleList<SimpleList> Base = new SimpleList<SimpleList>();

	int ID=0;
	JSONArray shapePathArray; 

	/*
	 * Funcion que agrega imagenes con todos sus parametros en la matriz
	 * @param name - nombre de la imagen
	 * @param author - autor de la imagen
	 * @param year - año en que se tomo la imagen
	 * @param size - tamano de la imagen
	 * @param description - descripcion de la imagen
	 */
	public void addImage(String name, String author, String year, String size, String description ) {

		SimpleList<String> newPic = new SimpleList<String>();
		newPic.add(String.valueOf(ID));
		newPic.add(name);
		newPic.add(author);
		newPic.add(year);
		newPic.add(size);
		newPic.add(description);
		Base.add(newPic);
		ID=ID+1;
		return;

	}

	/*
	 * Funcion que modifica datos de la imagen
	 * @param Id - numero de la imagen para saber cual cambiar
	 * @param pos - posicion de la informacion que se relaciona directamente con
	 * la informacion de cada imagen
	 * @param newData - Dato que sera escrito sobre el anterior
	 */
	public void modifyData(int ID, int pos,String newData) {
		for (int i=0;i<Base.size();i++) {
			if(Base.get(i).get(0).equals(String.valueOf(ID))) {
				Base.get(i).set(newData, pos);
				System.out.println("Changed succesfully");
			}	
		}
	}

	/*
	 * Funcion para remover una imagen
	 * @param Id - numero de la imagen para borrarse
	 */
	public void deleteImage(int ID) {
		String toDelete = String.valueOf(ID);
		for(int i=0;i<Base.size();i++) {
			if (toDelete.equals(Base.get(i).get(0))) {
				Base.remove(i);
				break;
			}
		}
	}

	/*
	 * Funcion para imprimir la matriz de informacion
	 */
	public void printMatrix() {
		for(int i=0;i<Base.size();i++) {
			for (int j=0; j<Base.get(i).size();j++) {
				System.out.print(Base.get(i).get(j));
				System.out.print(",");
			}
			System.out.println();

		}
	}

	/*
	 * Funcion para impirmir la lista de Huffman
	 */
	public void printList() {
		for (int i=0; i<Huff.size();i++) {
			System.out.print(Huff.get(i));
			System.out.print(",");
		}
		System.out.println();
	}

	/*
	 * Funcion para retornar Imagenes por solicitud
	 * @param request - informacion que se quiere comparar
	 * @param retur - informacion que se quiere sabar
	 * @param thing - string por comparar
	 * 
	 */
	@SuppressWarnings("null")
	public SimpleList<SimpleList> selectBy(int request, int retur,String thing){
		SimpleList<SimpleList> last= new SimpleList<SimpleList>();

		for (int i=0;i<Base.size();i++) {

			if (thing.equals(Base.get(i).get(request))) {
				SimpleList<String> tmp = new SimpleList<String>();
				tmp.add((String) Base.get(i).get(0));
				tmp.add((String)Base.get(i).get(retur));
				last.add(tmp);

			}
			else {

			}

		}
		return last;

	}

	/*
	 * Funcion de Huffman para los datos
	 * @param word - string por compresionar
	 * @return string de la compresion
	 */
	public String Huffman(String word) {
		Scanner s = new Scanner(System.in); 
		Huffman h = new Huffman();

		/*
		 * Condiciones iniciales para el Huffman
		 * Se asignan las listas de chars y de repeticion para 
		 * el string que se recibe
		 */

		h.charRep(word);

		SimpleList<Integer> rep = h.rep;
		SimpleList<String> letters = h.letters;

		int n = letters.size(); 

		/*
		 * Se crea un queue de prioridad
		 */

		PriorityQueue<HuffmanNode> q 
		= new PriorityQueue<HuffmanNode>(n, new MyComparator()); 

		for (int i = 0; i < n; i++) { 

			/*
			 * Se va creando el arbol, haciendo los nodos del mayor al menor 
			 */
			HuffmanNode hn = new HuffmanNode(); 

			hn.c = letters.get(i).toCharArray()[0]; 
			hn.data = rep.get(i); 

			hn.left = null; 
			hn.right = null; 

			/*
			 * Se agrega el nodo al queue tambien
			 */
			q.add(hn); 

		} 

		/* 
		 * Raiz del arbol
		 */
		HuffmanNode root = new HuffmanNode();
		/*
		 * Ya con el queue se van extrayendo los nodos de menor valor hasta que quede solo uno
		 */
		while (q.size() > 1) { 

			/*
			 * Primer nodo que se extrae 
			 */
			HuffmanNode x = q.peek(); 
			q.poll(); 

			/*
			 * Segundo nodo que se extrae 
			 */
			HuffmanNode y = q.peek(); 
			q.poll(); 

			/*
			 * Nuevo nodo creado
			 */
			HuffmanNode f = new HuffmanNode(); 

			/* 
			 * Se le asignan sus hijos con los nodos extraidos 
			 */
			f.data = x.data + y.data; 
			f.c = '-'; 

			f.left = x; 

			f.right = y; 

			root = f; 

			/*
			 * Se agrega al queue para que eventualmente vuelva a ser agregado
			 */
			q.add(f); 

		} 
		h.printCode(root, "");
		Huff = h.Table;
		Coded = storeCodes(Huff, word); 


		return Coded;
	} 

	/*
	 * Funcion para guardar la compresion del string
	 * @param Table - Tabla de Huffman
	 * @param str - string que se compresiona
	 * @return string resultante de la compresion
	 */
	public String storeCodes(SimpleList Table, String str){ 
		String Coding = "";
		//System.out.println("Size is "+str.length());
		for (int i=0; i<str.length();i++) {

			for (int j=0; j<Table.size();j=j+2) {
				if (Character.toString(str.charAt(i)).equals(Table.get(j))) {
					Coding+=Table.get(j+1);
				}
			}
		}
		return Coding;
	}

	/*
	 * Funcion que retorna un string del Json que se convierte
	 * @return string de la base
	 */
	public String toJson() {
		JSONObject obj = new JSONObject();

		// Crea el JSONArray
		shapePathArray = new JSONArray();
		for(int i = 0; i < Base.size(); i++) {
			String tmp = null;
			for(int j=0;j<Base.get(i).size();j++) {				
				tmp = (String) Base.get(i).get(j);
				shapePathArray.add(tmp);
			}
		}

		// Serializa cada uno de los atributos
		obj.put("shapePath", shapePathArray);

		// Lo convierte a JSON
		try (FileWriter file = new FileWriter("Source\\Mensajes.txt")) {
			file.write(Huffman(obj.toJSONString()));
			//file.write(obj.toJSONString());
			printList();
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj.toJSONString();
	}
}5