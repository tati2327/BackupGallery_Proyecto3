package com.server;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerJson {
	
	/*----------------------------JSONS para el raid----------------------*/
	
	/**
	 * Serializa la confirmación de si se reliazó o no el comando de UPDATE
	 * @param solution
	 * @return string en formato JSON
	 */
	public String serializeConfirmation(String solution) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", "14");
		myJson.put("confirmation", solution);

		return myJson.toJSONString();
	}
	
	public String serializeInsertImgRaid(byte[] image, int id) {
		JSONObject myJson = new JSONObject();
		JSONArray myImage = new JSONArray();
		
		for(int i = 0; i < image.length; i++) {
			myImage.add(image[i]);
		}
		
		myJson.put("request", "10");
		myJson.put("id", id);
		myJson.put("image", myImage);
		
		return myJson.toJSONString();
	}
	
	public String serializeGetImgRaid(String id) {
		JSONObject myJson = new JSONObject();
		
		myJson.put("request", "11");
		myJson.put("id", id);
		
		return myJson.toJSONString();
	}
	
	public String serializeDeleteImgRaid(String id) {
		JSONObject myJson = new JSONObject();
		
		myJson.put("request", "12");
		myJson.put("delete", id);
		
		return myJson.toJSONString();
	}

	/*----------------------------JSONS para la base de datos----------------------*/
	public String serializeInsertDB(String name, String author, int year, int size, String description) {
		JSONObject myJson = new JSONObject();
		
		myJson.put("request", "13");
		myJson.put("name", name);
		myJson.put("author", author);
		myJson.put("year", year);
		myJson.put("size", size);
		myJson.put("description", description);
		
		return myJson.toJSONString();
	}
	
	public String serializeName(String name) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", "1");
		myJson.put("name", name);

		return myJson.toJSONString();
	}
	
	/*----------------------------Obtener datos----------------------------------------*/
	public String getName(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String name = "";

		try {
			myJson = (JSONObject) parser.parse(objet);
			name = (String) myJson.get("name");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	public String getRequest(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String request = null;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (String) myJson.get("request");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return request;
	}
}
