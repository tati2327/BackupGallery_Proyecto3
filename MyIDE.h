#ifndef MYIDE_H
#define MYIDE_H

#include <QMainWindow>
#include "Client.h"

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

namespace Ui {
class myIDE;
}

class myIDE : public QMainWindow
{
    Q_OBJECT

public:
    explicit myIDE(QWidget *parent = nullptr);
    ~myIDE();
    void putData(int id, QString name, QString author,int year, int size, QString description);

    void identifyIDEFunction(QString text);

    void sendInsertCommand(QString text, int textIndex);

    void sendSelectCommand(QString text, int textIndex);

    void sendDeleteCommand(QString text, int textIndex);

    void sendUpdateCommand(QString text, int textIndex);

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void on_enterButton_clicked();

    void on_deleteButton_clicked();

private:
    Ui::myIDE *ui;
    Client myClient;
    JSON myJson;    
    
    
    /*! manageServer()
     *  Manejador de los mensajes del servidor
     */
    void manageClient();
    
    void readJSON(QString message);
    
    enum column{
        ID, NAME, AUTHOR, YEAR, SIZE, DESCRIPTION, OPEN, DELETE
    };
};

#endif // MYIDE_H
