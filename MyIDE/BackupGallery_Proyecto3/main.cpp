#include "MyIDE.h"
#include <QApplication>
#include <QDebug>
#include "mainwindow.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.show();
    return a.exec();
   /*

    Client CS;
    CS.conn();
    int i=0;
    while (true) {
        sleep(5);
        CS.send_data("Hola");
        i++;
        qDebug()<<"Mensaje #"<<i<<endl;
    }
    qDebug()<<"El mensaje es "<<QString::fromStdString(CS.readMessage());

    return 0; */
}
