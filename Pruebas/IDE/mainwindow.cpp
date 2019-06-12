#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "QString"
#include "QDebug"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_enterButton_clicked()
{
    QString textFromIDE;

    textFromIDE = ui->textEdit->toPlainText();

    qInfo() << textFromIDE;

}

void MainWindow::on_deleteButton_clicked()
{
    ui->textEdit->clear();
}

