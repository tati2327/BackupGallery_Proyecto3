#ifndef JSON_H
#define JSON_H

#include <iostream>
#include <allocators.h>
#include <document.h>
#include <prettywriter.h>
#include <fwd.h>
#include <stringbuffer.h>
#include <cstdio>
#include <writer.h>

using namespace rapidjson;
using namespace std;

class JSON {
    public:
        JSON();
        void jsonToDocument(string json);
        string serializeInsertMsg(int id, string name, string author, int year, int size, string description);
        string serializeDeleteMsg(string deleteMsg);
        string serializeUpdateMsg(string columnName, string value, string condition);
        //string serializeGraphic(List<int> resistance, List<int> uperStrenght, List<int> lowerStrenght,
          //                      List<int> emotionalInt, List<int> physicalCond, List<int> age, List<int> expectedGenerations,
            //                    List<int> survivalProb, List<int> fitness, List<int> id);

        int getRequest();
        void stringifyJSON();
};

/*
class JSON {
    public:


        int jsonToDocument(int request);
        void accesToDocument(int request);
        void request1(int _id, string _name, int _players);
        void request2(string _name, string _letter, int _x, int _y);
        void request3(string _name);
        void request4(string _name, bool _accept, int _score, bool _playing);
        void request5(string _name, bool _add);


    private:
        Document document;

};*/

#endif // JSON_H
