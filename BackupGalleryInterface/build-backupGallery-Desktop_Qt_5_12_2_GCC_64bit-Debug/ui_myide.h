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
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
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
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QTextEdit *textEdit;
    QWidget *horizontalLayoutWidget;
    QHBoxLayout *horizontalLayout_4;
    QPushButton *enterButton;
    QPushButton *deleteButton;
    QLabel *label_2;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *myIDE)
    {
        if (myIDE->objectName().isEmpty())
            myIDE->setObjectName(QString::fromUtf8("myIDE"));
        myIDE->resize(1132, 665);
        centralWidget = new QWidget(myIDE);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        tableMetadata = new QTableWidget(centralWidget);
        if (tableMetadata->columnCount() < 8)
            tableMetadata->setColumnCount(8);
        if (tableMetadata->rowCount() < 1)
            tableMetadata->setRowCount(1);
        tableMetadata->setObjectName(QString::fromUtf8("tableMetadata"));
        tableMetadata->setGeometry(QRect(470, 40, 651, 241));
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
        verticalLayoutWidget = new QWidget(centralWidget);
        verticalLayoutWidget->setObjectName(QString::fromUtf8("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(480, 310, 641, 251));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        textEdit = new QTextEdit(verticalLayoutWidget);
        textEdit->setObjectName(QString::fromUtf8("textEdit"));

        verticalLayout->addWidget(textEdit);

        horizontalLayoutWidget = new QWidget(centralWidget);
        horizontalLayoutWidget->setObjectName(QString::fromUtf8("horizontalLayoutWidget"));
        horizontalLayoutWidget->setGeometry(QRect(690, 570, 231, 31));
        horizontalLayout_4 = new QHBoxLayout(horizontalLayoutWidget);
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_4->setObjectName(QString::fromUtf8("horizontalLayout_4"));
        horizontalLayout_4->setContentsMargins(0, 0, 0, 0);
        enterButton = new QPushButton(horizontalLayoutWidget);
        enterButton->setObjectName(QString::fromUtf8("enterButton"));
        enterButton->setFont(font1);

        horizontalLayout_4->addWidget(enterButton);

        deleteButton = new QPushButton(horizontalLayoutWidget);
        deleteButton->setObjectName(QString::fromUtf8("deleteButton"));
        deleteButton->setFont(font1);

        horizontalLayout_4->addWidget(deleteButton);

        label_2 = new QLabel(centralWidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setGeometry(QRect(750, 290, 121, 16));
        label_2->setFont(font1);
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
        enterButton->setText(QApplication::translate("myIDE", "Enter", nullptr));
        deleteButton->setText(QApplication::translate("myIDE", "Delete", nullptr));
        label_2->setText(QApplication::translate("myIDE", "MyIDE Editor", nullptr));
    } // retranslateUi

};

namespace Ui {
    class myIDE: public Ui_myIDE {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MYIDE_H
