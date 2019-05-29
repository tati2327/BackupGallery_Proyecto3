/********************************************************************************
** Form generated from reading UI file 'myide.ui'
**
** Created by: Qt User Interface Compiler version 5.12.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MYIDE_H
#define UI_MYIDE_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_myIDE
{
public:
    QWidget *centralWidget;
    QTableWidget *tableMetadata;
    QLabel *label;
    QPushButton *pushButton;
    QPushButton *pushButton_2;
    QLabel *lblPicture;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *myIDE)
    {
        if (myIDE->objectName().isEmpty())
            myIDE->setObjectName(QString::fromUtf8("myIDE"));
        myIDE->resize(1132, 642);
        centralWidget = new QWidget(myIDE);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        tableMetadata = new QTableWidget(centralWidget);
        if (tableMetadata->columnCount() < 8)
            tableMetadata->setColumnCount(8);
        if (tableMetadata->rowCount() < 1)
            tableMetadata->setRowCount(1);
        tableMetadata->setObjectName(QString::fromUtf8("tableMetadata"));
        tableMetadata->setGeometry(QRect(470, 40, 651, 261));
        QFont font;
        font.setFamily(QString::fromUtf8("Liberation Serif"));
        font.setPointSize(8);
        font.setItalic(true);
        tableMetadata->setFont(font);
        tableMetadata->setRowCount(1);
        tableMetadata->setColumnCount(8);
        tableMetadata->horizontalHeader()->setDefaultSectionSize(80);
        label = new QLabel(centralWidget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(720, 10, 141, 31));
        QFont font1;
        font1.setFamily(QString::fromUtf8("Liberation Serif"));
        font1.setPointSize(9);
        font1.setItalic(true);
        label->setFont(font1);
        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setGeometry(QRect(1000, 10, 89, 25));
        pushButton_2 = new QPushButton(centralWidget);
        pushButton_2->setObjectName(QString::fromUtf8("pushButton_2"));
        pushButton_2->setGeometry(QRect(180, 480, 89, 25));
        pushButton_2->setFont(font1);
        lblPicture = new QLabel(centralWidget);
        lblPicture->setObjectName(QString::fromUtf8("lblPicture"));
        lblPicture->setGeometry(QRect(20, 40, 421, 421));
        myIDE->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(myIDE);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 1132, 22));
        myIDE->setMenuBar(menuBar);
        mainToolBar = new QToolBar(myIDE);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        myIDE->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(myIDE);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        myIDE->setStatusBar(statusBar);

        retranslateUi(myIDE);

        QMetaObject::connectSlotsByName(myIDE);
    } // setupUi

    void retranslateUi(QMainWindow *myIDE)
    {
        myIDE->setWindowTitle(QApplication::translate("myIDE", "myIDE", nullptr));
        label->setText(QApplication::translate("myIDE", "Informaci\303\263n de la b\303\272squeda", nullptr));
        pushButton->setText(QApplication::translate("myIDE", "TEST", nullptr));
        pushButton_2->setText(QApplication::translate("myIDE", "Agregar", nullptr));
        lblPicture->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class myIDE: public Ui_myIDE {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MYIDE_H
