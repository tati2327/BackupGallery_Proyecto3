#ifndef SOCKETS_CLIENT_H
#define SOCKETS_CLIENT_H

#include <string>
#include <netinet/in.h>
#include "Client.h"
#include <string>
#include <bits/socket.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <cstring>
#include <iostream>
#include <arpa/inet.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdlib.h>

using namespace std;

class Client {
    private:

        string IP;
        int PORT;

    public:
        int clientSocket;
        Client();
        bool conn();
        bool send_data(string data);
        string readMessage();
};


#endif //SOCKETS_CLIENT_H
