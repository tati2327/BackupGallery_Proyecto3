#include "Client.h"

Client::Client() {
    
}

void Client::newClient(string ipAddress, int port) {
    this->port = port;
    this->ipAddress = ipAddress;
    
    /*! Creo el socket del cliente*/
    sockClient = socket(AF_INET, SOCK_STREAM, 0);
    if (sockClient == -1) {
        cout<<"Error al crear el socket del cliente"<<endl;
        return;
    }

    /*! Crear una estructura para el servidor con el que nos estamos conectando*/
    sockaddr_in hint{};
    hint.sin_family = AF_INET;
    hint.sin_port = htons(static_cast<uint16_t>(port));
    inet_pton(AF_INET, ipAddress.c_str(), &hint.sin_addr);

    /*! Conectar al servidor en el socket*/
    int connectRes = connect(sockClient, (sockaddr *) &hint, sizeof(hint));
    if (connectRes == -1) {
        cout<<"Error al intentar la conexión"<<endl;
        return;
    }
}

