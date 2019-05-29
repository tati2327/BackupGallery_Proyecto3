#ifndef CLIENT_H
#define CLIENT_H

#include <iostream>
#include <sys/types.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>
#include <string.h>
#include <string>
#include "JSON.h"
#include<stdio.h>
#include<stdlib.h>

using namespace std;

class Client{
    public:
        int sockClient; /*!< Variable para guardar el socket del cliente*/
        string ipAddress /*!< Direccion ip*/;
        int port; /*!< Numero de puerto*/
        
        /*! Constructor para crear un cliente */
        Client();

        /*! newClient()
         *  Funcion para crear el socket y conectarse a un servidor
         */
        void newClient(string ipAddress, int port);
};

#endif // CLIENT_H
