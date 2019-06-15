package com;

import com.Node;
/** 
 * <h1>SimpleList.</h1>
 * Implementación de una lista enlazada. Implemeta varias operaciones de las listas y permite 
 * todos los objetos a excepción de los datos no primitivos. La clase proveé métodos para insertar, 
 * eliminar y mostrar datos, tanto al inicio como al final de la lista.
 * Declarar una lista:<br>
 * 			{@code ListaSimple<T> name = ListaSimple<>();}
 * 
 * @param <T> - El tipo de los elementos que countiene la lista.
 * @author Esteban Alvzarado Vargas | Pablo Carmona Ulate | Tatiana Chacón Mora
 * @version 7/08/2018
 * 
 */
public class SimpleList <T> {
	
	private Node<T> first;
	private int size;
	
	/**
	 * Constructor de una lista vacía.
	 */
	public SimpleList() {
		this.first = null;
		this.size = 0;
	}
	
	/**
	 * Retorna el numero de elementos de la lista.
	 * @return tamaño de la lista
	 */
	public int size() {
		return size;
	}
	
	public void deleteall() {
		this.first = null;
		this.size = 0;
	}
	/**
	 * Inserta un dato específico al inicio de la lista. 
	 * @param data  tipo de elemento que soporta la lista. 
	 */
	public void addFirst(T data) {
		Node<T> newData = new Node<T>(data);
		newData.next = first;
		first = newData;
		this.size++;
	}
	
	
	/** 
	 * Añade un dato específico al final de la lista. 
	 * @param data - tipo de elemento que soporta la lista. 
	 */
	public void add(T data) {
		
		if(first == null) {
			
			first = new Node <T>(data);
			this.size++;
			
		} else {
			
			Node<T> aux = first;
			
			while(aux.next!= null) {
				aux = aux.next;
			}
			
			aux.next = new Node <T>(data);
			this.size++;
		}
	}
	
	
	/**
	 * Remueve la primer aparición del elemento específicado de la lista, si este está presente. 
	 * @param data - data que se desea eliminar de la lista.
	 * @return Confirma que el dato es eliminado.
	 */
	public boolean remove(T data) {
		
		Node<T> aux= first, prev = null;
		boolean answer = false;
		
		while(aux != null && !aux.data.equals(data)) {
			prev = aux;
			aux = aux.next;
		}
		if(aux != null) {
			answer = true;
			this.size--;
			
			if(prev == null) {
				first = aux.next;
			} else {
				prev.next = aux.next;
			}
		}
		
		return answer;
	}
	
	
	/**
	 * Remueve el elemento de la posición de lalista especificada.
	 * @param index - Número de la posición de la lista. 
	 * @return Confirma que el dato es eliminado.
	 */
	public boolean remove(int index) {
		
		Node<T> aux= first, prev = null;
		boolean answer = false;
		int count = 0;
		
		while(count != index) {
			count++;
			prev = aux;
			aux = aux.next;
		}
		if(aux != null) {
			answer = true;
			this.size--;
			
			if(prev == null) {
				first = aux.next;
			} else {
				prev.next = aux.next;
			}
		}
		return answer;
	}
	
	/**
	 * Retorna la lista como String.
	 * @return {@link String} - lista.
	 */
	public String showList() {
		
		String rtn = "";
		
		for(Node<T>aux = first; aux != null; aux= aux.next) {
			if(aux.next == null) {
			rtn += aux.data.toString();
			} else {
				rtn += aux.data.toString() + ",";
			}
		}
		return "["+rtn+"]";
	}
	
	
	/**
	 * Método que recibe un indice y muestra(Retorna un String) el data que se encuentra en ese indice de la lista
	 * RECORDAR QUE EL PRIMER INDICE ES CERO
	 * @param index Posicion en la lista del elemento a mostrar
	 * @return String de la lista
	 */
	public String showForIndex(int index) {
		
		Node<T> aux = first;
		int count = 0;
		String answer = "";
		
		while(count != index) {
			count++;
			aux = aux.next;
		}
		if(aux != null) {
			answer += aux.data;
		}
		return "["+answer+"]";	
	}
	
	/**
	 * Retorna el elemento ubicado en la posición especificada en la lista.
	 * @param index - Posición en la lista del elemento a retornar. 
	 * @return dato ubicado en el indice.
	 */
	public T get(int index) {
		
		Node<T> aux = first;
		int count = 0;
		// En lugar de retornar un
		T answer = null;
		
		while(count != index) {
			count++;
			aux = aux.next;
		}
		if(aux != null) {
			answer = aux.data;
		}
		return answer;	
	}
	
	/**
	 * Retorna el elemento dado, en la lista.
	 * @param data - elemento a retornar. 
	 * @return dato que se desea obtener.
	 */
	public T get(T data) {
		
		Node<T> aux = first;
		T answer = null;
		
		while(aux != null) {
			
			if(aux.data.equals(data)) {
				answer = aux.data;
				break;
			}
			
			aux = aux.next;
		}
		
		return answer;
	}
	
	/**
	 * Reemplaza el elemento de la posición especificada en la lista, por el nuevo dato especificado. 
	 * @param data - elemento que será almacenado en la posición especifica.
	 * @param index - indice del elemento a reemplazar.
	 * @return el elemento previo de la posición especificada.
	 */
	public T set(T data,int index) {
		
		Node<T> aux = first;
		T answer = null;
		int count = 0;
		
		while(count != index) {
			count++;
			aux = aux.next;
		}
		if(aux != null) {
			answer = aux.data;
			aux.data = data;
		}
		return answer;
	}
}