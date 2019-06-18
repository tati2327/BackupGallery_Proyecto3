#ifndef LIST_H
#define LIST_H

#include <iostream>
#include "Node.h"

template<typename T>
class List {
public:
    Node<T> *first; /*!< Primer elemento de la lista*/
    Node<T> *last; /*!< Ultimo elemento de la lista*/
    Node<T> *curr; /*!< Nodo que funciona para facilitar recorrer la lista en ciertas funciones */

    /*!
     * List()
     *Constructor
     */
    List();

    /*!#include "Field.h"
     * add()
     * Agrega un nuevo nodo a la lista.
     * @param dato de tipo T
     */
    void add(T data);

    /*!
     * deleteNode()
     * Elimina un nodo de la lista por dato.
     * @param data de tipo T
     */
    void deleteNode(T _data);

    void deleteIndex(int index);

    /*!
    * getData()
    * Retorna un dato de tipo T
    * @param un indice
    * @return un dato de tipo T
    */
    T getData(int index);

    /*!
     * getNode()
     * Retorna un nodo de tipo T
     * @param un indice
     * @return un nodo de tipo T
     */
    Node<T> *getNode(int index);

    /*!
     * Imprime la lista genérica
     * PRECAUCUÓN: La imprime mientras sean datos primitivos NO OBJETOS de una clase "X"
     */
    void show();

    /*!
     * size()
     * Retorna el tamaño de la lista
     * @return un int con la cantidad de elementos de la lista
     */
    int size();

    /*!
     *  operator []
     *  Sobrecarga del operador [] para obtener la posición de un elemento de la lista
     * @param un indice
     * @return el dato de un elemento de la lista
     */
    T operator [](int index);

    void cleanList();
};

#include "List_def.h"

#endif // LIST_H
