#include "mainwindow.h"
#include <QApplication>
#include <iostream>
#include <allocators.h>
#include <cstdio>
#include <document.h>
#include <prettywriter.h>
#include <fwd.h>
#include <stringbuffer.h>
#include "JSON.h"

using namespace rapidjson;
using namespace std;
/*
int main(int, char*[]) {

    JSON j;
    string hi = j.serializeDeleteMsg("salud");
    j.jsonToDocument(hi);

    //j.accesToDocument(1);
    //j.request1(45, "ss",4);
    //j.stringifyJSON();
}*/


int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();

    return a.exec();
}
