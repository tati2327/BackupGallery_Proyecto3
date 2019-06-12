#include <iostream>
#include "Client.h"

int main() {
    Client c;

    c.conn();
    c.send_data("Test Class C++ Client \n");

    return 0;
}