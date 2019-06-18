using namespace std;

template<typename T>
List<T>::List() {
    first = nullptr;
    last = nullptr;
    curr = nullptr;
}

template<typename T>
void List<T>::add(T data) {
    if(first == nullptr) {
        /*! Si la lista esta vacia */
        first = new Node<T>;
        first->data = data;
        first->next = nullptr;
        last = first;
    } else {
        /*! Si la lista NO esta vacia */
        if(last == first) {
            /*! Si la lista tiene solo UN elemento */
            last = new Node<T>;
            last->data = data;
            last->next = nullptr;
            first->next = last;
        } else {
            /*! Si la lista tiene mas de un elemento */
            Node<T>* newData = new Node<T>;
            newData->data = data;
            newData->next = nullptr;
            last->next = newData;
            last = newData;
        }
    }
}

template<typename T>
void List<T>::deleteNode(T _data) {
    if (first != nullptr) {
        Node<T> *auxDelete;
        auxDelete = first;

        while ((auxDelete != nullptr) && (auxDelete->data != _data)) {
            curr = auxDelete;
            auxDelete = auxDelete->next;
        }

        /*! No se encontró el elemento*/
        if (auxDelete == nullptr) {
            cout << "El elemento no existe" << endl;
        } else if (curr == nullptr) {
            /*! El elemento es el primero de la lista */
            if(this->size() == 1){
                first = nullptr;
                last = nullptr;
                delete(auxDelete);
                return;
            }

            first = first->next;
            delete (auxDelete);
            return;
        } else if (auxDelete == last) {
            /*! El elemento esta al final*/
            last = curr;
            curr->next = auxDelete->next;
            delete (auxDelete);
            return;
        } else {
            /*!El elemento esta en el medio*/
            curr->next = auxDelete->next;
            delete (auxDelete);
            return;
        }
    }
}

template <typename T>
void List<T>::deleteIndex(int index) {
    if (first != nullptr) {
        Node<T> *auxDelete;
        auxDelete = first;

        int count=0;
        while ((auxDelete != nullptr) && index!= count) {
            curr = auxDelete;
            auxDelete = auxDelete->next;
            count++;
        }

        /*! No se encontró el elemento*/
        if (auxDelete == nullptr) {
            cout << "El elemento no existe"<<endl;
        } else if (curr == nullptr) {
            /*! El elemento es el primero de la lista */
            if(this->size() == 1){
                first = nullptr;
                last = nullptr;
                delete(auxDelete);
                return;
            }

            first = first->next;
            delete(auxDelete);
            return;
        } else if(auxDelete == last) {
            /*! El elemento esta al final*/
            last = curr;
            curr->next = auxDelete->next;
            delete(auxDelete);
            return;
        } else{
            /*!El elemento esta en el medio*/
            curr->next = auxDelete->next;
            delete(auxDelete);
            return;
        }
    }
}

template<typename T>
T List<T>::getData(int index) {
    if(index == 0) {
        // Get the first element
        return first->data;
    } else {
        // Get the index'th element
        curr = first;
        for(int i = 0; i < index; ++i) {
            curr = curr->next;
        }
        return curr->data;
    }
}

template<typename T>
Node<T>* List<T>::getNode(int index) {
    if(index == 0) {
        // Get the first element
        return first;
    } else {
        // Get the index'th element
        Node<T>* curr = this->first;
        for(int i = 0; i < index; ++i) {
            curr = curr->next;
        }
        return curr;
    }
}

template<typename T>
void List<T>::show(){
    curr = first;
    while (curr!= nullptr){
        cout << curr->getValue()<<endl;
        curr = curr->getNext();
    }
}

template<typename T>
int List<T>::size(){
    curr = first;
    int count=0;

    while(curr != nullptr){     /*! La lista se recorre con .getNext() y se tiene un contador para que
                             *  crezca segun se recorra la lista*/
        curr = curr->getNext();
        count=count+1;
    }

    //cout<<"El tamaño de la lista es "<<count<<endl;
    return count;
}

template<typename T>
T List<T>::operator [](int index) {
    return getData(index);
}

template<typename T>
void List<T>::cleanList() {
    int size = this->size();
    if(size==0) return;
    for(int i = 0; i < size; i++){
        this->deleteIndex(0);
    }
}
