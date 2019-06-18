#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QDialog>
#include "MyIDE.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QDialog
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

private:
    Ui::MainWindow *ui;
    myIDE x;
};

#endif // MAINWINDOW_H
