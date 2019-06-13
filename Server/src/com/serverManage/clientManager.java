package com.serverManage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.server.ManageConections;
import com.server.ServerJson;

public class clientManager {
	
	ServerJson myJson = new ServerJson();
	ManageConections myManageConections = new ManageConections();
	
	public void readMessage(String message) {
		int request = myJson.getRequest(message);
		
		switch (request) {
		case 2: //SELECT
			myManageConections.sendMessage("dataBase", message);
		case 3: //UPDATE
			myManageConections.sendMessage("dataBase", message);
		case 4: //DELETE
			myManageConections.sendMessage("dataBase", message);
		case 5: //INSERT
			JSONParser parser = new JSONParser();
			JSONObject myJsonTemp;
			String name ="", author="", description="";
			int year=0, size=0;

			try {
				myJsonTemp = (JSONObject) parser.parse(message);
				name = (String) myJsonTemp.get("name");
				author = (String) myJsonTemp.get("author");
				description = (String) myJsonTemp.get("description");
				year = (int) myJsonTemp.get("year");
				size = (int) myJsonTemp.get("size");
				
				myManageConections.saveImages((byte[]) myJsonTemp.get("image"));
				myManageConections.sendMessage("dataBase", myJson.serializeInsertDB(name, author, year, size, description));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			break;
		}		
	}
}