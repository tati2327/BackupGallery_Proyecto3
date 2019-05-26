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
    document.AddMember("age", nameString, allocator);
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

int JSON::getRequest() {
    return document["request"].GetInt();
}

void JSON::stringifyJSON() {
    cout<<"Modified JSON with reformatting: "<<endl;
    StringBuffer sb;
    PrettyWriter<StringBuffer> writer(sb);
    document.Accept(writer);    // Accept() traverses the DOM and generates Handler events.
    puts(sb.GetString());
}
