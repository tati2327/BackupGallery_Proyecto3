#include <cstdio>
#include <cstring>
#include "JSON.h"
#include "JSON.h"
#include <cstdio>

using namespace rapidjson;
using namespace std;

Document document(kObjectType);

JSON::JSON() = default;

void JSON::jsonToDocument(string json) {
    document.Parse(json.c_str());
    cout<<"De json a documento exitosamente!!"<<endl;
}

string JSON::serializeInsertMsg(int id, string name, string author, int year, int size,
                           string description) {
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();


    Value nameString(name.c_str(), allocator);
    Value authorString(author.c_str(), allocator);
    Value descriptionString(description.c_str(), allocator);
    document.AddMember("request",1, allocator);
    document.AddMember("id", id, allocator);
    document.AddMember("name", nameString, allocator);
    document.AddMember("author", authorString, allocator);
    document.AddMember("year", year, allocator);
    document.AddMember("size", size, allocator);
    document.AddMember("description", descriptionString, allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"***********************************************************"<<endl;
    return strbuf.GetString();
}

string JSON::serializeDeleteMsg(string deleteMsg){
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    Value deleteString(deleteMsg.c_str(), allocator);
    document.AddMember("request", 4, allocator);
    document.AddMember("delete", deleteString, allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"***********************************************************"<<endl;
    return strbuf.GetString();
}

string JSON::serializeUpdateMsg(string columnName, string value, string condition){
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    Value columnNameString(columnName.c_str(), allocator);
    Value valueString(value.c_str(), allocator);
    Value conditionString(condition.c_str(), allocator);

    document.AddMember("request", 3, allocator);
    document.AddMember("columnName", columnNameString, allocator);
    document.AddMember("value", valueString, allocator);
    document.AddMember("condition", conditionString, allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"***********************************************************"<<endl;
    return strbuf.GetString();
}

string JSON::serializeSelectMsg(string columnName){
   
        document.RemoveAllMembers();
        Document::AllocatorType& allocator = document.GetAllocator();
    
        Value columnNameString(columnName.c_str(), allocator);
       
        document.AddMember("request", 3, allocator);
        document.AddMember("columnName", columnNameString, allocator);
      
    
        StringBuffer strbuf;
        Writer<StringBuffer> writer(strbuf);
        document.Accept(writer);
    
        cout<<"***********************************************************"<<endl;
        std::cout <<strbuf.GetString()<< std::endl;
        cout<<"***********************************************************"<<endl;
        return strbuf.GetString();
    
}


int JSON::getRequest() {
    return document["request"].GetInt();
}

