#ifndef SOCKETS_CLIENT_H
#define SOCKETS_CLIENT_H

#include <string>
#include <netinet/in.h>

using namespace std;

class Client {
    private:
        int sock;
        string address;
        int port;
        struct sockaddr_in server;

    public:
        Client();
        bool conn();
        bool send_data(string data);
        string readMessage();
        // string receive(int);

};


#endif //SOCKETS_CLIENT_H
