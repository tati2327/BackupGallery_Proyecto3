package com.dataBase;

import com.dataBase.HuffmanNode;
import com.structures.SimpleList;

public class Huffman { 
	/*
	 * Lista que contiene la tabla que tiene el char y su valor binario 
	 */
	public SimpleList<String>  Table= new SimpleList<String>();
	/*
	 * Lista de repeticiones de un char en un string 
	 */
	public SimpleList<Integer> rep= new SimpleList<Integer>();
	/*
	 * Lista que contiene los char de un string 
	 */
	public SimpleList<String> letters = new SimpleList<String>();


	/*
	 * Funcion que imprime el arbol de Huffman
	 * @param root - Inicio del arbol
	 * @param str - string es el resultado binario del string
	 */
	public void printCode(HuffmanNode root, String str){ 

		/*
		 * Caso base
		 */
		if (root.left == null
				&& root.right == null
				&& Character.isLetter(root.c)) { 

			//			System.out.println(root.c + ":" + str); 

			/*
			 * Se agrega el char como string en la tabla y luego su valor en binario
			 */
			Table.add(Character.toString(root.c));
			Table.add(str);

			return; 
		} 

		/*
		 * Llamada recursiva para seguir haciendo el arbol
		 * Si va hacia la izquierda se asigna un 0, sino un 1
		 */
		if (root.left==null || root.right==null) {

		}
		else {
			printCode(root.left, str + "0"); 
			printCode(root.right, str + "1"); 
		}

	} 

	/*
	 * Funcion que asigna todos los char de un string en una lista y tambien las repeticiones
	 * del mismo string en otra
	 * @param word - string al que se le quiere aplicar la funcion
	 */
	public void charRep(String word) {

		boolean flag=false;
		/*
		 * Se recorre el string, si no hay letras en la lista letters, se agrega el primero
		 */
		for(int i=0;i<word.length();i++) {
			if (letters.size()==0) {
				letters.add(Character.toString(word.charAt(0)));
				rep.add(1);
			}
			else {
				/*
				 * Revisa que la letra del string coincida con alguna que ya este en la lista
				 * si es igual se enciende una bandera para que continue, sino se enciende
				 * significa que la letra no esta y se agrega a las listas
				 */
				for(int j=0;j<letters.size();j++) {
					if(letters.get(j).toCharArray()[0]==word.charAt(i)) {
						rep.set(rep.get(j)+1, j);
						flag=true;
						break;
					}
					else {

					}

				}
				if(!flag) {
					letters.add(Character.toString(word.charAt(i)));
					rep.add(1);
				}
				else {
					flag=false;
				}

			}
		}
		/*
		 * Llamada para ordenar la lista de mayor a menor para aplicar el Huffman
		 */
		bubbleSort(rep);

		//System.out.println(letters.showList());
		//System.out.println(rep.showList());

	}

	/*
	 * Bubblesort con la variante que como hay dos listas y cada una corresponden sus posiciones
	 * si cambio dos elementos en una lista tambien lo tengo que hacer en la otra
	 * @param arr - lista de las repeticiones de los char para acomodarlos de mayor a menor
	 */
	void bubbleSort(SimpleList<Integer> arr) { 
		int n = arr.size(); 
		for (int i = 0; i < n-1; i++) 
			for (int j = 0; j < n-i-1; j++) 
				if (arr.get(j) < arr.get(j+1)){ 
					// swap arr[j+1] and arr[i] 
					int temp = arr.get(j); 
					String temp2 = letters.get(j);
					arr.set(arr.get(j+1), j); 
					arr.set(temp, j+1);
					letters.set(letters.get(j+1), j);
					letters.set(temp2, j+1);
				} 
	} 
}
