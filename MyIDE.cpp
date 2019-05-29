#include "MyIDE.h"
#include "ui_myide.h"
#include <QFileDialog>
#include <QMessageBox>
#include <QDebug>

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
    int sockClient = myClient.sockClient;
    
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
        int bytesReceived = static_cast<int>(recv(myClient.sockClient, buf, 4096, 0));
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
        QImage image(filename);
        ui->lblPicture->setPixmap(QPixmap::fromImage(image));
        qDebug()<<filename;
    }
}

void myIDE::on_enterButton_clicked()
{
    QString textFromIDE;
    textFromIDE = ui->textEdit->toPlainText();
    qInfo() << textFromIDE;

    identifyIDEFunction(textFromIDE);

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
    //qInfo() << text.at(textIndex);
    qInfo() << txtFunction;

    if(txtFunction == "INSERT"){

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
             qInfo() << "name validation: "<< tableNameValidation;

             if(tableNameValidation == "invencibleLibrary"){

                 QStringRef txtFunction(&text, textIndex+1, 6);

                 //!Validates command VALUES
                 if(txtFunction == "VALUES"){

                     textIndex = text.indexOf("(");

                     qInfo() <<"value of ("<<textIndex;
                     qInfo()<< text;

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
                     qInfo() << "text after name: " << txtFunction3;
                     text = txtFunction3.toString();

                     //!Extract text for year
                     textIndex = text.indexOf(" ");
                     QStringRef year(&text,0,textIndex-1);

                     qInfo() << "year: " <<year;

                     QStringRef txtFunction4(&text, textIndex+1, text.size()-textIndex-1);
                     qInfo() << "text after name: " << txtFunction4;
                     text = txtFunction4.toString();

                     //!Extract text for size
                     textIndex = text.indexOf(" ");
                     QStringRef size(&text,0,textIndex-1);

                     qInfo() << "size: " << size;

                     QStringRef txtFunction5(&text, textIndex+1, text.size()-textIndex-1);
                     qInfo() << "text after name: " << txtFunction5;
                     text = txtFunction5.toString();

                     //!Extract text for description
                     textIndex = text.indexOf(")");
                     QStringRef description(&text,0,textIndex);

                     qInfo() << "description: " << description;



                 }
                //TEST INSERT INTO invencibleLibrary VALUES (FAJKHAS,DSJDHSSD,DSDD)
             }
        }



        //QStringRef tableNameValidation

    }

}
