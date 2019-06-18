#include "Client.h"

using namespace std;

Client::Client(){
    clientSocket = -1;
    PORT = 4444;
    IP = "192.168.100.14";
}

/**
 * Connect to a host on a certain port number
 */
bool Client::conn(){
    if(clientSocket == -1){

        clientSocket = socket(AF_INET , SOCK_STREAM , 0);
        if (clientSocket == -1){
            perror("Could not create socket");
        }
    }

    sockaddr_in hint{};
    hint.sin_family = AF_INET;
    hint.sin_port = htons(static_cast<uint16_t>(PORT));
    inet_pton(AF_INET, IP.c_str(), &hint.sin_addr);

    /*! Conectar al servidor en el socket*/
    int connectRes = connect(clientSocket, (sockaddr *)&hint, sizeof(hint));

    if (connectRes < 0){
        perror("La conexión fallo. ERROR: ");
        return 1;
    }


    cout<<"Conectado"<<endl;
    return true;
}

/**
 * Send data to the connected host
 */
bool Client::send_data(string data){

    /*! Enviar mensaje al servidor*/
    int sendRes = send(clientSocket, data.c_str(), strlen(data.c_str()), 0);
    if (sendRes == -1){
        cout << "No se pudo mandar mensaje al servidor!!"<<endl;
        return false;
    }

    cout<<"Mensaje enviado"<<endl;
    return true;
}

string Client::readMessage(){
    char buf[4096];
    string messageReived;
    cout<<"Voy a comenzar a recibir el mensaje..."<<endl;
    int bytesReceived = recv(clientSocket, buf, 4096, 0);
    messageReived = string(buf,bytesReceived);
    return messageReived;
}
