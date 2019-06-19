package Sockets;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Raid.RaidManage;

public class ManageMessages {
	
	String toSend=null;
	ClientJson myJson = new ClientJson();
	RaidManage myRaid = new RaidManage();

	/**
	 * Lee el mensaje que llega del servidor y segun sea la 
	 * solicitud retorna un mensaje de respuesta
	 * @param message
	 * @return
	 */
	public String readMessage(String message) {
		String request=myJson.getRequest(message);
		JSONParser parser = new JSONParser();
		JSONObject myJsonObject = new JSONObject();
		int id=0;
		
		try {
			myJsonObject = (JSONObject) parser.parse(message);
			
			switch (request) {
			case "6": //Cuando el server pide una imagen por el boton abrir
				id = (int) myJsonObject.get("id");
				byte[] image=null;
				try {
					image = myRaid.getImage(id);
					return toSend=myJson.serializeSendIdImg(image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case "15": //Add image
				JSONArray myImage = new JSONArray();
				myImage = (JSONArray) myJsonObject.get("image");
				
				byte[] newImage=null;
				
				for(int i=0; i<myImage.size(); i++) {
					newImage[i] = (byte) myImage.get(i);
				}
				
				return toSend=myJson.serializeConfirmation("true");
			case "7": //DELETE IMAGE BUTTON
				id = (int) myJsonObject.get("id");
				myRaid.delete(id);
				
				return toSend=myJson.serializeConfirmation("true");
			case "13": //DELETE IMAGE desde la base de datos
				JSONArray ids = new JSONArray();
				ids = (JSONArray) myJsonObject.get("idList");
				int temp=0;
				
				for(int i=0; i<ids.size(); i++) {
					temp = (int) ids.get(i);
					myRaid.delete(temp);
				}
				
				return toSend = myJson.serializeConfirmation("true");
				
			default:
				break;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
