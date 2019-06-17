package com;

/**
 * Un nodo es un objeto independiente y dinámico. Debe poseer al menos dos atributos:
 * Un valor y un nodo siguiente.
 * @author Esteban Alvarado Vargas
 * @param T - Tipo del dato que soportará el Nodo.
 * @version 4/08/2018
 */
public class Node <T>{
	
	protected T data;
	protected Node <T> next;
	
	/** Constructor de un Nodo.
	 * @param data - elemento que se guardará en el nodo.
	 */
	public Node(T data){
		this(data,null);
	}
	
	/** 
	 * Constructor de un Nodo. Recibe dos parámetros dato y nodo siguiente.
	 * @param data - elemento que se guardará en el nodo.
	 * @param next - Valor del nodo siguiente.
	 */
	private Node(T data, Node <T> next){
		this.data = data;
		this.next = next;
	
	}
}