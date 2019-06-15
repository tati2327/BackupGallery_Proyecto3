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
#include <stdio.h>
#include <stdlib.h>
#include <fstream>
#include <sstream>

namespace Ui {

//!Class myIDE: user interacting class
/*!
 * This is the class that serves as an interfacte, for the user
 * be able to communicate with NoSQL database and RAID 5
 */
class myIDE;
}

class myIDE : public QMainWindow
{
    Q_OBJECT

public:
    //!Default constructor
    explicit myIDE(QWidget *parent = nullptr);

    //!Default Destructor
    ~myIDE();

    //FALTA COMENTAR
    void putData(int id, QString name, QString author,int year, int size, QString description);

    //!identifyIDEFunction
    /*!
     * \param text: text received from user at textBox
     * Recognizes if the first command typed is either UPDATE, INSERT, SELECT, DELETE.
     * If not, sends user message to retype an approved command.
     */
    void identifyIDEFunction(QString text);

    //!sendInsertCommand
    /*!
     * \param text: text received from user at textBox
     * \param textIndex: character position indicator
     * Validates command and receive name, author, year, size and description
     * Then forms a JSON string and send it to server
     */
    void sendInsertCommand(QString text, int textIndex);

    //!sendSelectCommand
    /*!
     * \param text: text received from user at textBox
     * \param textIndex: character position indicator
     * Validates command and receive name of the column or * (for all columns) desired
     * Then forms a JSON string and send it to server
     */
    void sendSelectCommand(QString text, int textIndex);

    //!sendDeleteCommand
    /*!
     * \param text: text received from user at textBox
     * \param textIndex: character position indicator
     * Validates command and receive column and value that conditions the rows in the
     * table that will be deleted. Then forms a JSON string and send it to server
     */
    void sendDeleteCommand(QString text, int textIndex);

    //!sendUpdateCommand
    /*!
     * \param text: text received from user at textBox
     * \param textIndex: character position indicator
     * Validates command and receive column and value that will be setted
     * and the column and value that conditions the rows in the table that
     * will be updated. Then forms a JSON string and send it to server
     */
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
