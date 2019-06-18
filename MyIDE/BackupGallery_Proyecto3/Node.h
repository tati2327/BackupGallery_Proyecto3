#ifndef NODE_H
#define NODE_H

/*!
 * Class Node<T>
 * Clase con nodos de tipo T para usarlos en una lista enlazada
 * @tparam T
 */
template<typename T>
class Node {
public:
    Node<T>* next; /*!< Puntero al siguiente nodo*/
    T data; /*!< Dato que almacena el nodo */

    /*!
     * getValue()
     * Retorna el valor del nodo
     * @return dato T
     */
    T getValue();

    /*!
     * setValue()
     * Cambia el dato de un nodo
     * @param recibe un dato T
     */
    void setData(T _data);

    /*!
     * setNode()
     * Cambia un "Nodo" por otro
     * @param recibe un nodo de tipo T
     */
    void setNode(Node<T>* _node);

    /*!
     * getNext()
     * Retorna el siguiente nodo de un nodo en específico
     * @return un nodo de tipo T
     */
    Node<T> * getNext();
};

#include "Node_def.h"

#endif // NODE_H
