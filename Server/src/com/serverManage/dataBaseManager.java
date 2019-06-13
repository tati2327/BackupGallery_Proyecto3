package com.serverManage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.server.ManageConections;
import com.server.ServerJson;

public class dataBaseManager {
	
	ServerJson myJson = new ServerJson();
	ManageConections myManageConections = new ManageConections();
	
	public void readMessage(String message) {
		int request = myJson.getRequest(message);
		
		switch (request) {
		case 7: //Confirmacion del UPDATE
			myManageConections.sendMessage("client", message);
			break;
		case 8: //ID de la imagen para el raid
			JSONParser parser = new JSONParser();
			JSONObject myJsonTemp;
			int id=0;
			
			try {
				myJsonTemp = (JSONObject) parser.parse(message);
				id = (int) myJsonTemp.get("id");
				
				myManageConections.sendMessage("raid", myJson.serializeInsertImgRaid(myManageConections.getImage(), id));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 14: //DELETE 
			myManageConections.sendMessage("raid", message);
			break;
		case 9: //Send metadata
			myManageConections.sendMessage("client", message);
			break;
		default:
			break;
		}		
	}
}
