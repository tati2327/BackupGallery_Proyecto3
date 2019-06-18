#include "MyIDE.h"
#include "ui_myide.h"
#include <QFileDialog>
#include <QMessageBox>
#include <QDebug>
#include <QBuffer>
#include <string>


myIDE::myIDE(QWidget *parent) :QMainWindow(parent), ui(new Ui::myIDE)
{
    QStringList titles;
    titles <<"ID"<<"Nombre"<<"Autor"<<"Año"<<"Tamaño"<<"Descripción"<<" "<<" ";

    ui->setupUi(this);
    setWindowTitle("Backup Gallery");

    ui->tableMetadata->setColumnCount(8);
    ui->tableMetadata->setHorizontalHeaderLabels(titles);

   QPixmap pix("/home/tati2327/Documentos/Github/Proyecto3/BackupGallery_Proyecto3/BackupGalleryInterface/backupGallery/Resources/imageDefault.jpg");
   ui->lblPicture->setPixmap(pix);

    //myClient.newClient("192.168.100.9", 54000);
    //manageClient();
}

myIDE::~myIDE()
{
    delete ui;
}

void myIDE::putData(int id, QString name, QString author,int year, int size, QString description)
{
    int row = ui->tableMetadata->rowCount()-1;

    ui->tableMetadata->insertRow(ui->tableMetadata->rowCount());
    ui->tableMetadata->setItem(row, ID, new QTableWidgetItem(QString::number(id)));
    ui->tableMetadata->setItem(row, NAME, new QTableWidgetItem(name));
    ui->tableMetadata->setItem(row, AUTHOR, new QTableWidgetItem(author));
    ui->tableMetadata->setItem(row, YEAR, new QTableWidgetItem(QString::number(year)));
    ui->tableMetadata->setItem(row, SIZE, new QTableWidgetItem(QString::number(size)));
    ui->tableMetadata->setItem(row, DESCRIPTION, new QTableWidgetItem(description));

    QPushButton* openButton = new QPushButton("Abrir");
    QPushButton* deleteButton = new QPushButton("Eliminar");

    openButton->minimumWidth();
    openButton->minimumSize();
    openButton->minimumSizeHint();
    deleteButton->minimumWidth();

    ui->tableMetadata->setCellWidget(row, OPEN, (QWidget*)openButton);
    ui->tableMetadata->setCellWidget(row, DELETE, (QWidget*)deleteButton);
}

/* boton de prueba*/
void myIDE::on_pushButton_clicked()
{
    putData(10, "Pepe", "Grillo", 2019, 450, "Buena foto");
}

void myIDE::manageClient() {
    int sockClient = myClient.clientSocket;
    
    char buf[4096];
    string messageReived;
    string messageToSend;

    while(true) {
        JSON myJsonToSend; /*!< Instancia del Json para mandar mensajes*/

        int sendRes=-1;
        /*! Enviar mensaje al servidor*/
        if (sendRes == -1){
            //cout << "No se pudo mandar mensaje al servidor!!"<<endl;
            continue;
        }


        /*! Espera una respuesta...*/
        memset(buf, 0, 4096);
        int bytesReceived = static_cast<int>(recv(myClient.clientSocket, buf, 4096, 0));
        if (bytesReceived == -1){
            cout <<"Hubo un error al obtener respuesta del servidor"<<endl;
            close();
        }else{
            /*! Se lee la respuesta del servidor*/
            messageReived = string(buf, static_cast<unsigned long>(bytesReceived));

            cout<<"SERVER: "<<messageReived << endl;
            readJSON(QString::fromStdString(messageReived));
            sleep(1);
        }
    }
}

void myIDE::readJSON(QString message)
{
    myJson.jsonToDocument(message.toStdString());
    /* 1. Validar cuantas columnas son
     * 2. A
        */
            
}

/*Boton para agregar imágenes*/
void myIDE::on_pushButton_2_clicked()
{
    QFileDialog dialog(this);
    dialog.setNameFilter(tr("Images (*.png *.xpa *.jpg)"));
    dialog.setViewMode(QFileDialog::Detail);
    QString filename = QFileDialog::getOpenFileName(
                this, tr("Open Images"), "C://",
                "All files(*.*)");
    if(!filename.isEmpty()){
        //QImage image(filename);
        //ui->lblPicture->setPixmap(QPixmap::fromImage(image));
        qDebug()<<filename;

        //Bioland (leche, pinito, miel, (comer antes de entrenar y despues, batido))

        QList<int> byteList = imageToByteQList(filename);

        byteQListToImage(byteList);

        QImage imagen ("/home/fabian/CLionProjects/BackupGallery_Proyecto3/Resources/Image7.png");

        ui->lblPicture->setPixmap(QPixmap::fromImage(imagen));

    }
}

void myIDE::byteQListToImage(QList<int> byteList){

    std::ofstream ofp("/home/fabian/CLionProjects/BackupGallery_Proyecto3/Resources/Image7.png", std::ofstream::binary);

    int length = byteList.size();

    char buffer4[length];

    // convert the integer values to unsigned char

    for(int j=0; j<length; j++){
        buffer4[j]=(unsigned char)byteList.at(j);
    }

    if (!ofp) {
      cout << "Can't open file:" << endl;
      exit(1);
    }

    ofp.write(buffer4, length);

    if (ofp.fail()){
      cout << "Can't write image " << endl;
      exit(0);
    }
     ofp.close();
}

QList<int> myIDE::imageToByteQList(QString filename){
    //METODO 2
    //open file

    std::ifstream infile(filename.toStdString());

    //get length of file
    infile.seekg(0, infile.end);
    size_t length = infile.tellg();
    infile.seekg(0, infile.beg);
    char buffer[length];

    // don't overflow the buffer!
    if (length > sizeof (buffer))
    {
        length = sizeof (buffer);
    }
    //read file
    infile.read(buffer, length);


    //qDebug() << "/pos0 :"<< QByteArray::fromRawData(buffer,length);
    QList<int> byteList;
    string byteString;

    for(int i=0; i< length; i++){
        byteList.append(int(buffer[i]));

        byteString.append(string(1,buffer[i]));
    }

    /*Para mostrar lista de bytes
    for(int i =0; i<byteList.size(); i++){
        qDebug()<<byteList.at(i);
    }*/

    infile.close();
    return byteList;
}

void myIDE::on_enterButton_clicked()
{
    QString textFromIDE;
    textFromIDE = ui->textEdit->toPlainText();
    qInfo() <<endl << "Text recieved from IDE: " << textFromIDE <<endl;
    identifyIDEFunction(textFromIDE);
    ui->textEdit->clear();
}

void myIDE::on_deleteButton_clicked()
{
    ui->textEdit->clear();
}

void myIDE::identifyIDEFunction(QString text){

    int textIndex = 0;
    textIndex = text.indexOf(" ");

    QStringRef txtFunction(&text,0,textIndex);
    //qInfo() <<textIndex+1;
    qInfo() << "--------------------------------------------------------------------------------------";
    qInfo() <<"Executing Command: " << txtFunction;

    if(txtFunction == "INSERT"){

        sendInsertCommand(text,textIndex);
        qInfo() << "--------------------------------------------------------------------------------------";
    }

    else if(txtFunction == "SELECT"){

        sendSelectCommand(text, textIndex);
        qInfo() << "--------------------------------------------------------------------------------------";
    }

    else if(txtFunction == "DELETE"){

        sendDeleteCommand(text, textIndex);
        qInfo() << "--------------------------------------------------------------------------------------";
    }

    else if(txtFunction == "UPDATE"){
        sendUpdateCommand(text, textIndex);
        qInfo() << "--------------------------------------------------------------------------------------";
    }
}

void myIDE::sendUpdateCommand(QString text, int textIndex){//Test: UPDATE invencibleLibrary SET Year=2019 WHERE ID=3;

    //!Extracts tablename
    QStringRef txtFunction(&text, textIndex+1, text.size()-(textIndex+1));
    textIndex = txtFunction.indexOf(" ");
    text = txtFunction.toString();
    QStringRef tablenameValidation(&text,0,textIndex);
    qInfo() <<"Extract tablename: " << tablenameValidation;

    //!Validates if table name provided is correct
    if (tablenameValidation == "invencibleLibrary"){

        //!Extract SET word
        QStringRef txtFunction(&text, textIndex+1, 3);
        qInfo()<<"Extract SET: "<<txtFunction;

        if(txtFunction=="SET"){

            //!Extract column for SET
            QStringRef txtFunction(&text, textIndex+5, text.size()-(textIndex+5));
            textIndex = txtFunction.indexOf("=");
            int textIndex2 = txtFunction.indexOf(" ");
            text = txtFunction.toString();
            QStringRef columnNameSetted(&text, 0, textIndex);
            qInfo()<< "set column: " << columnNameSetted;

            if(columnNameSetted == "ID" || columnNameSetted == "Author" || columnNameSetted == "Year"
                    || columnNameSetted == "Size" || columnNameSetted == "Description"){

                //!Extracts value updated
                QStringRef valueSetted(&text,textIndex+1,textIndex2-(textIndex+1));
                qInfo()<< "Value setted: " << valueSetted;

                //!Extracts WHERE word
                QStringRef txtFunction(&text, textIndex2+1, 5);
                qInfo()<< "Extract WHERE: " << txtFunction;

                //!Validates WHERE Word
                if(txtFunction == "WHERE"){

                    //!Extract column for condition
                    QStringRef txtFunction(&text,textIndex2+7, text.size()-(textIndex2+7));
                    textIndex= txtFunction.indexOf("=");
                    text = txtFunction.toString();
                    QStringRef columnNameValidation(&text,0,textIndex);
                    qInfo()<<"Column condition: "<<columnNameValidation;

                    //!Validates if column name is in the table
                    if(columnNameValidation == "ID" || columnNameValidation == "Author" || columnNameValidation == "Year"
                            || columnNameValidation == "Size" || columnNameValidation == "Description"){

                        //!Extract value of condition
                        QStringRef columnNameData(&text,textIndex+1,text.size()-(textIndex+2));

                        qInfo()<<"value of condition: " << columnNameData;

                        myJson.serializeUpdateMsg(qPrintable(columnNameSetted.toString()),qPrintable(valueSetted.toString()),qPrintable(columnNameValidation.toString()),qPrintable(columnNameData.toString()));
                    }else{
                        qInfo()<<"column name not typed correctly";
                    }
                }
            }else{
                qInfo()<<"Not recognized column name next to SET command";
            }
        }else{
            qInfo("SET word recieved incorrectly, check spaces");
        }
    }else{
        qInfo()<<"table name provided is incorrect";
    }
}

void myIDE::sendDeleteCommand(QString text, int textIndex){ //Test: DELETE FROM invencibleLibrary WHERE ID=3;

    //!Extracts FROM
    QStringRef txtFunction(&text, textIndex+1, text.size()-(textIndex+1));
    textIndex = txtFunction.indexOf(" ");
    text = txtFunction.toString();
    QStringRef txtFunction2(&text,0,textIndex);
    qInfo() <<"Extract FROM: " << txtFunction2;

    //!Validates if it will be extracted a column or all columns
    if (txtFunction2== "FROM"){

        //!Extracts tablename
        QStringRef txtFunction(&text, textIndex+1, text.size()-(textIndex+1));
        textIndex = txtFunction.indexOf(" ");
        text = txtFunction.toString();
        QStringRef tablenameValidation(&text, 0, textIndex);
        qInfo() <<"table name: " << tablenameValidation;

        if(tablenameValidation == "invencibleLibrary"){

            //!Extract WHERE word
            QStringRef txtFunction(&text, textIndex+1, 5);
            qInfo()<< "Extract WHERE: "<< txtFunction;

            //!Validate if WHERE typed correctly
            if(txtFunction == "WHERE"){

                //!Extract column name
                QStringRef txtFunction(&text, textIndex+7, text.size()-(textIndex+7));
                textIndex= txtFunction.indexOf("=");
                text = txtFunction.toString();
                QStringRef columnNameValidation(&text,0,textIndex);
                qInfo()<<"Column condition: "<<columnNameValidation;

                //!Validates if column name is in the table
                if(columnNameValidation == "ID" || columnNameValidation == "Author" || columnNameValidation == "Year"
                        || columnNameValidation == "Size" || columnNameValidation == "Description"){

                    //!Extract value of condition
                    QStringRef columnNameData(&text,textIndex+1,text.size()-(textIndex+2));
                    qInfo()<<"value of condition: " << columnNameData;
                    myJson.serializeDeleteMsg(qPrintable(columnNameValidation.toString()),qPrintable(columnNameData.toString()));

                }else{
                    qInfo()<<"column name not typed correctly";
                }
            }else{
                qInfo()<<"WHERE word not typed correctly";
            }
        }else{
            qInfo()<<"Table name typed incorrectly.";
        }
    }else{
        qInfo()<< "not typed FROM clause correctly";
    }
}

void myIDE::sendSelectCommand(QString text, int textIndex){ //Test: SELECT * FROM invencibleLibrary;

    //!Extracts column or (*)
    QStringRef txtFunction(&text, textIndex+1, text.size()-(textIndex+1));
    textIndex = txtFunction.indexOf(" ");
    text = txtFunction.toString();
    QStringRef txtFunction2(&text,0,textIndex);
    qInfo() <<"Extract column: " << txtFunction2;

     //!Validates if it will be extratect a column or all columns
    if(txtFunction2 == "*" || txtFunction2 == "ID" || txtFunction2 == "Author"
            || txtFunction2 == "Year" || txtFunction2 == "Size" || txtFunction2 == "Description"){

        myJson.serializeSelectMsg(qPrintable(txtFunction2.toString()));
        //!Extracts FROM
         QStringRef txtFunction(&text, textIndex+1,4);

         //text = txtFunction.toString();
         qInfo() << "Validate FROM: " << txtFunction;

         //!Extracts the name of the table
         QStringRef txtFunction2(&text, textIndex+6, text.size()-(textIndex+7));
         qInfo() << "table_name validation: "<< txtFunction2;

         //!Verify the name of the table
         if(txtFunction2 == "invencibleLibrary"){
             qInfo() << "command executed sucessfully";
         }else{
             qInfo() << "tablename not excecuted correctly";
         }
    }else {
        qInfo() << "not recognized column name provided.";
    }
}

void myIDE::sendInsertCommand(QString text, int textIndex){  //TEST INSERT INTO invencibleLibrary VALUES (FAJ, HAS, DSJD, HSSD, DSDD);
    //!Extracts command INTO
    QStringRef txtFunction(&text,textIndex+1,4);

    //!Validates if command id written correctly
    if(txtFunction == "INTO"){

        //!Extracts text after INTO statement to the rest of the text
         QStringRef txtFunction(&text, textIndex+6, text.size()-(textIndex+6));
         textIndex = txtFunction.indexOf(" ");
         text = txtFunction.toString();

         //!Extracts the name of the tableInserted
         QStringRef tableNameValidation(&text, 0, textIndex);

         //!VALIDATES IF TABLE NAME PROVIDED IS EQUAL TO TABLE NAME OF THE NoSQL Table of the system
         qInfo() << "table_name validation: "<< tableNameValidation;

         if(tableNameValidation == "invencibleLibrary"){

             QStringRef txtFunction(&text, textIndex+1, 6);

             //!Validates command VALUES
             if(txtFunction == "VALUES"){

                 textIndex = text.indexOf("(");
                 QStringRef txtFunction(&text, textIndex, text.size()-textIndex);
                 qInfo() <<"text after VALUES : "<< txtFunction;
                 text = txtFunction.toString(); //SIEMPRE DEBE IR POSTERIOR A LA LLAMADA DE QSTRINGREF

                 //!Extract text for name
                 textIndex = text.indexOf(" ");
                 QStringRef name(&text,1,textIndex-2);
                 qInfo() << "name: " <<name;
                 QStringRef txtFunction2(&text, textIndex+1, text.size()-textIndex-1);
                 qInfo() << "text after name: " << txtFunction2;
                 text = txtFunction2.toString();

                 //!Extract text for author
                 textIndex = text.indexOf(" ");
                 QStringRef author(&text,0,textIndex-1);
                 qInfo() << "author: " <<author;
                 QStringRef txtFunction3(&text, textIndex+1, text.size()-textIndex-1);
                 qInfo() << "text after author: " << txtFunction3;
                 text = txtFunction3.toString();

                 //!Extract text for year
                 textIndex = text.indexOf(" ");
                 QStringRef year(&text,0,textIndex-1);
                 qInfo() << "year: " <<year;
                 QStringRef txtFunction4(&text, textIndex+1, text.size()-textIndex-1);
                 qInfo() << "text after year: " << txtFunction4;
                 text = txtFunction4.toString();

                 //!Extract text for size
                 textIndex = text.indexOf(" ");
                 QStringRef size(&text,0,textIndex-1);
                 qInfo() << "size: " << size;
                 QStringRef txtFunction5(&text, textIndex+1, text.size()-textIndex-1);
                 qInfo() << "text after size: " << txtFunction5;
                 text = txtFunction5.toString();

                 //!Extract text for description
                 textIndex = text.indexOf(")");
                 QStringRef description(&text,0,textIndex);
                 qInfo() << "description: " << description;

                 myJson.serializeInsertMsg(qPrintable(txtFunction2.toString()), qPrintable(txtFunction3.toString()),qPrintable(txtFunction4.toString()),qPrintable(txtFunction5.toString()),qPrintable(description.toString()));
             }
         }
    }
}
