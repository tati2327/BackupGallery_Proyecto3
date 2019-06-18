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

string JSON::serializeInsertMsg(string name, string author, string year, string size, string description) {
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    Value nameString(name.c_str(), allocator);
    Value authorString(author.c_str(), allocator);
    Value descriptionString(description.c_str(), allocator);
    Value yearString(year.c_str(),allocator);
    Value sizeString(size.c_str(),allocator);

    document.AddMember("request",5, allocator);
    document.AddMember("name", nameString, allocator);
    document.AddMember("author", authorString, allocator);
    document.AddMember("year", yearString, allocator);
    document.AddMember("size", sizeString, allocator);
    document.AddMember("description", descriptionString, allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"***********************************************************"<<endl;
    return strbuf.GetString();
}

string JSON::serializeDeleteMsg(string deleteMsg, string deleteValue){
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    Value deleteString(deleteMsg.c_str(), allocator);
    Value deleteValueString(deleteValue.c_str(), allocator);

    document.AddMember("request", 4, allocator);
    document.AddMember("condition", deleteString, allocator);
    document.AddMember("value", deleteValueString, allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"***********************************************************"<<endl;
    return strbuf.GetString();
}

string JSON::serializeUpdateMsg(string columnName, string value, string condition, string valueCondition){
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    Value columnNameString(columnName.c_str(), allocator);
    Value valueString(value.c_str(), allocator);
    Value conditionString(condition.c_str(), allocator);
    Value valueConditionString(valueCondition.c_str(),allocator);

    document.AddMember("request", 3, allocator);
    document.AddMember("columnName", columnNameString, allocator);
    document.AddMember("value", valueString, allocator);
    document.AddMember("condition", conditionString, allocator);
    document.AddMember("valueCondition", valueConditionString, allocator);

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
       
        document.AddMember("request", 2, allocator);
        document.AddMember("columnName", columnNameString, allocator);
      
        StringBuffer strbuf;
        Writer<StringBuffer> writer(strbuf);
        document.Accept(writer);
    
        cout<<"***********************************************************"<<endl;
        std::cout <<strbuf.GetString()<< std::endl;
        cout<<"***********************************************************"<<endl;
        return strbuf.GetString();
}

string JSON::serializeOpenImage(int id){
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    document.AddMember("request", 15, allocator);
    document.AddMember("id", id, allocator);

    StringBuffer strbuf;
    Writer<StringBuffer> writer(strbuf);
    document.Accept(writer);

    cout<<"***********************************************************"<<endl;
    std::cout <<strbuf.GetString()<< std::endl;
    cout<<"***********************************************************"<<endl;
    return strbuf.GetString();
}

string JSON::serializeDeleteImage(int id){
    document.RemoveAllMembers();
    Document::AllocatorType& allocator = document.GetAllocator();

    document.AddMember("request", 16, allocator);
    document.AddMember("id", id, allocator);

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

int JSON::getYear(){
    return document["year"].GetInt();
}

string JSON::getName(){
    return document["name"].GetString();
}

string JSON::getDescription(){
    return document["description"].GetString();
}

string JSON::getAuthor(){
    return document["author"].GetString();
}

int JSON::getId(){
    return document["id"].GetInt();
}

int JSON::getSize(){
    return document["size"].GetInt();
}

