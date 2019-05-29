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
