#include "MyIDE.h"
#include <QApplication>
#include "List.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    myIDE w;
    w.show();
    return a.exec();
}
