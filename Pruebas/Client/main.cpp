#include <iostream>
#include "Client.h"

int main() {
    Client c;

    c.conn();
    int i =0;
    string message;
    while(i<20) {
        cin>>message;
        c.send_data(message);
        i++;
    }
    return 0;
}