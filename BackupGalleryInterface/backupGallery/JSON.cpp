#include "JSON.h"
#include <cstdio>
#include <cstring>

Document document(kObjectType);
JSON::JSON() = default;

void JSON::jsonToDocument(string json) {
    document.Parse(json.c_str());
    cout<<"De json a documento exitosamente!!"<<endl;
}

string JSON::serializeRequest() {
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();
    document.AddMember("request",1, allocator);
    document.AddMember("message","nuevo juego", allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"*****************messageServer******************************************"<<endl;
    return strbuf.GetString();
}

int JSON::getRequest(){
    return document["request"].GetInt();
}

int JSON::getSizeData()
{
    return document["size"].GetInt();    
}

List<string> JSON::getValue1()
{
    List<string> value;
    Value temp = document["value1"].GetArray();
    for(int i = 0; i < temp.Size(); i++){
        value.add(temp[i].GetString());
    }
    return value;   
}

List<string> JSON::getValue2()
{
    List<string> value;
    Value temp = document["value2"].GetArray();
    for(int i = 0; i < temp.Size(); i++){
        value.add(temp[i].GetString());
    }
    return value; 
}

List<string> JSON::getValue3()
{
    List<string> value;
    Value temp = document["value3"].GetArray();
    for(int i = 0; i < temp.Size(); i++){
        value.add(temp[i].GetString());
    }
    return value; 
}

List<string> JSON::getValue4()
{
    List<string> value;
    Value temp = document["value4"].GetArray();
    for(int i = 0; i < temp.Size(); i++){
        value.add(temp[i].GetString());
    }
    return value; 
}

List<string> JSON::getValue5()
{
    List<string> value;
    Value temp = document["value5"].GetArray();
    for(int i = 0; i < temp.Size(); i++){
        value.add(temp[i].GetString());
    }
    return value; 
}

List<string> JSON::getValue6()
{
    List<string> value;
    Value temp = document["value6"].GetArray();
    for(int i = 0; i < temp.Size(); i++){
        value.add(temp[i].GetString());
    }
    return value; 
}
