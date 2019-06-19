package com.serverManage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.server.ManageConections;
import com.server.ServerJson;

public class dataBaseManager {
	
	ServerJson myJson = new ServerJson();
	ManageConections myManageConections = new ManageConections();
	
	//Mensajes que recibe 
	public void readMessage(String message) {
		String request = myJson.getRequest(message);
		
		switch (request) {
		case "10": //CONFIRMATION
			myManageConections.sendMessage("client", message);
			break;
		case "11": //ID de la imagen (Para el RAID)
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
		case "12": //SEND METADA 
			myManageConections.sendMessage("client", message);
			break;
		case "13": //DELETE
			myManageConections.sendMessage("raid", message);
			break;
		default:
			break;
		}		
	}
}
