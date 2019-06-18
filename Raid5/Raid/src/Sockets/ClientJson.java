package Sockets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientJson {

	/*---------------------------- SERIALIZAR ----------------------*/

	/**
	 * Serializa la confirmación de si se reliazó o no la solicitud del servidor
	 * @param solution
	 * @return string en formato JSON
	 */
	public String serializeConfirmation(boolean solution) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", 9);
		myJson.put("confirmation", solution);

		return myJson.toJSONString();
	}
	
	/**
	 * Serializa el nombre del cliente
	 * @param name - Nombre del cliente
	 * @return
	 */
	public String serializeName(String name) {
		JSONObject myJson = new JSONObject();

		myJson.put("request", 1);
		myJson.put("name", name);

		return myJson.toJSONString();
	}

	/**
	 * Serializa laa imagen que solicita el servidor
	 * @param id
	 * @return string en formato JSON
	 */
	public String serializeSendIdImg(byte[] image) {
		JSONObject myJson = new JSONObject();
		JSONArray myImage = new JSONArray();
		
		for(int i=0; i<image.length; i++) {
			myImage.add(image[i]);
		}
		
		myJson.put("request", 8);
		myJson.put("image", myImage);

		return myJson.toJSONString();
	}

	/*---------------------------- DESERIALIZAR ----------------------------------------*/

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
	
	public byte[] getImage(String objet) {

		JSONParser parser = new JSONParser();
		JSONObject myJson = new JSONObject();
		JSONArray myArray = new JSONArray();
		
		byte[] myImage = null;

		try {
			myJson = (JSONObject) parser.parse(objet);
			myArray = (JSONArray) myJson.get("image");
			
			for(int i=0; i<myArray.size(); i++){
				myImage[i] = (byte) myArray.get(i);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myImage;
	}
}
