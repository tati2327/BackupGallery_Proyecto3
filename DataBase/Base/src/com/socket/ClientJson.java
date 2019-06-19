package com.socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.structures.SimpleList;

public class ClientJson {

	/*------------------------------------ SERIALIZAR -----------------------------*/

	/**
	 * Serializa la confirmación de si se reliazó o no el comando de UPDATE
	 * @param solution
	 * @return string en formato JSON
	 */
	public String serializeConfirmation(String solution) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", "10");
		myJson.put("confirmation", solution);

		return myJson.toJSONString();
	}
	
	/**
	 * 
	 * @param name - Nombre del cliente
	 * @return
	 */
	public String serializeName(String name) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", "1");
		myJson.put("name", name);

		return myJson.toJSONString();
	}

	/**
	 * Serializa el id de la imagen que acaba de guardar en la base de 
	 * datos para que el servidor se lo mande al RAID5
	 * @param id
	 * @return string en formato JSON
	 */
	public String serializeSendIdImg(String id) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", "11");
		myJson.put("id", id);

		return myJson.toJSONString();
	}

	/**
	 * Serializa el id de la imagen que acaba de eliminar en la base de 
	 * datos para que el servidor se lo mande al RAID5
	 * @param id
	 * @return string en formato JSON
	 */
	public String serializeDeleteImg(int[] id) {
		JSONObject myJson = new JSONObject();
		JSONArray myArray = new JSONArray();
				
		for(int i=0; i<id.length; i++) {
			myArray.add(id[i]);
		}
		
		myJson.put("request", "13");
		myJson.put("idList", myArray);

		return myJson.toJSONString();
	}

	public String serializeMetadata(SimpleList<SimpleList> base) {
		JSONObject myJson = new JSONObject();
		JSONArray myMetadata = new JSONArray();
		JSONArray packages = new JSONArray();

		for(int i=0; i<base.size(); i++) {
			for(int j=0; j<base.get(i).size(); j++) {
				packages.add(base.get(i).get(j));
			}
			myMetadata.add(packages);
			packages.clear();
		}
		
		myJson.put("request", "12");
		myJson.put("data", myMetadata);

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
	
	public String getAuthor(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String author = "";

		try {
			myJson = (JSONObject) parser.parse(objet);
			author = (String) myJson.get("author");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return author;
	}
	
	public String getYear(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String year = null;

		try {
			myJson = (JSONObject) parser.parse(objet);
			year = (String) myJson.get("year");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return year;
	}
	
	public String getSize(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String size = null;

		try {
			myJson = (JSONObject) parser.parse(objet);
			size = (String) myJson.get("size");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return size;
	}
	
	public String getDescription(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String description = "";

		try {
			myJson = (JSONObject) parser.parse(objet);
			description = (String) myJson.get("description");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return description;
	}
	
	public String getColumnName(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String columnName = "";

		try {
			myJson = (JSONObject) parser.parse(objet);
			columnName = (String) myJson.get("columnName");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return columnName;
	}
	
	public String getValue(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String value = "";

		try {
			myJson = (JSONObject) parser.parse(objet);
			value = (String) myJson.get("value");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;
	}
	
	public String getCondition(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String condition = "";

		try {
			myJson = (JSONObject) parser.parse(objet);
			condition = (String) myJson.get("condition");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return condition;
	}
	
	public String getId(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson;
		String id = null;

		try {
			myJson = (JSONObject) parser.parse(objet);
			id = (String) myJson.get("id");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
}

