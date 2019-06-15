#include "Client.h"
#include <string>
#include <bits/socket.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <cstring>
#include <iostream>
#include <arpa/inet.h>


using namespace std;


Client::Client()
{
    sock = -1;
    port = 54000;
    address = "192.168.100.14 ";
}

/**
    Connect to a host on a certain port number
*/
bool Client::conn()
{
    //create socket if it is not already created
    if(sock == -1)
    {
        //Create socket
        sock = socket(AF_INET , SOCK_STREAM , 0);
        if (sock == -1)
        {
            perror("Could not create socket");
        }
    }
    server.sin_addr.s_addr = inet_addr( address.c_str() );
    server.sin_family = AF_INET;
    server.sin_port = htons( port );
    //Connect to remote server
    if (connect(sock , (struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("connect failed.## Error");
        return 1;
    }
    cout<<"Connected\n";
    return true;
}

/**
    Send data to the connected host
*/
bool Client::send_data(string data)
{
    //Send some data
    if( send(sock , data.c_str() , strlen( data.c_str() ) , 0) < 0)
    {
        perror("Send failed : ");
        return false;
    }
    cout<<"Mensaje enviado del cliente #"<<sock<<endl;
    return true;
}

string Client::readMessage(){
    char buf[4096];
    string messageReived;
    cout<<"Voy a comenzar a recibir el mensaje..."<<endl;
    int bytesReceived = static_cast<int>(recv(sock, buf, 4096, 0));
    messageReived = string(buf, static_cast<unsigned long>(bytesReceived));
    return messageReived;
}
