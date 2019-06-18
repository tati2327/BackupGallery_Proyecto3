#ifndef JSON_H
#define JSON_H

#include <iostream>
#include "allocators.h"
#include "stringbuffer.h"
#include "document.h"
#include "writer.h"
#include "List.h"
#include "QString"

using namespace rapidjson;
using namespace std;

class JSON {
    public:
        JSON();
        void jsonToDocument(string json);

        string serializeRequest();
        string serializeInsertMsg(string name, string author, string year, string size, string description);
        string serializeDeleteMsg(string deleteMsg, string deleteValue);
        string serializeUpdateMsg(string columnName, string value, string condition, string valueCondition);
        string serializeSelectMsg(string columnName);
        string serializeOpenImage(int id);
        string serializeDeleteImage(int id);

        int getRequest();
        int getYear();
        string getName();
        string getDescription();
        string getAuthor();
        int getId();
        int getSize();
        //??? getImage();


};

#endif // JSON_H
