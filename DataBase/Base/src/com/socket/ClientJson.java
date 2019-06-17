package com.socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientJson {

	/*---------------------------- SERIALIZAR ----------------------*/

	/**
	 * Serializa la confirmación de si se reliazó o no el comando de UPDATE
	 * @param solution
	 * @return string en formato JSON
	 */
	public String serializeConfirmationUṕdate(boolean solution) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", 7);
		myJson.put("solution", solution);

		return myJson.toJSONString();
	}

	/**
	 * Serializa el id de la imagen que acaba de guardar en la base de 
	 * datos para que el servidor se lo mande al RAID5
	 * @param id
	 * @return string en formato JSON
	 */
	public String serializeSendIdImg(int id) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", 8);
		myJson.put("id", id);

		return myJson.toJSONString();
	}

	/**
	 * Serializa el id de la imagen que acaba de eliminar en la base de 
	 * datos para que el servidor se lo mande al RAID5
	 * @param id
	 * @return string en formato JSON
	 */
	public String serializeDeleteImg(int id) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", 14);
		myJson.put("delete", id);

		return myJson.toJSONString();
	}

	/*----------------------------JSONS para la base de datos----------------------*/
	public String serializeInsertDB(String name, String author, int year, int size, String description) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", 13);
		myJson.put("name", name);
		myJson.put("author", author);
		myJson.put("year", year);
		myJson.put("size", size);
		myJson.put("description", description);

		return myJson.toJSONString();
	}

	/*---------------------------- DESERIALIZAR ----------------------------------------*/
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

	public int getRequest(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("request");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getAuthor(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("author");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getYear(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("year");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getSize(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("size");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getDescription(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("description");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getColumnName(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("columnName");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getValue(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("value");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getCondition(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("condition");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
	
	public int getId(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		int request = 0;

		try {
			myJson = (JSONObject) parser.parse(objet);
			request = (int) myJson.get("id");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
}

