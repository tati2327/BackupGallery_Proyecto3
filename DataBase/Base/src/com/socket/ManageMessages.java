package com.socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dataBase.BaseNoSql;

public class ManageMessages {

	String toSend=null;
	ClientJson myJson = new ClientJson();
	BaseNoSql myDataBase = new BaseNoSql();

	/**
	 * Lee el mensaje que llega del servidor y segun sea la 
	 * solicitud retorna un mensaje de respuesta
	 * @param message
	 * @return
	 */
	public String readMessage(String message) {
		int request=myJson.getRequest(message);
		JSONParser parser = new JSONParser();
		JSONObject myJsonObject = new JSONObject();

		try {
			myJsonObject = (JSONObject) parser.parse(message);
			
			//Variables que se usan en más de un caso
			String valueCondition, columnCondition;
			int columnConditionValue;


			switch (request) {
			case 2: //SELECT
				String columnName = myJsonObject.get(arg0)

				return toSend;
			case 3: //UPDATE
				String columnChange = (String) myJsonObject.get("columnName");
				String valueChange = (String) myJsonObject.get("value");
				columnCondition = (String) myJsonObject.get("condition");
				valueCondition = (String) myJsonObject.get("valueContition");

				int columnChangeValue = this.getColumnValue(columnChange);
				columnConditionValue = this.getColumnValue(columnCondition);

				if(myDataBase.modifyData(columnConditionValue, valueCondition, columnChangeValue, valueChange)) {
					toSend = myJson.serializeConfirmation(true);
					return toSend;
				}

				toSend = myJson.serializeConfirmation(false);
				return toSend;
			case 4: //DELETE
				columnCondition = (String) myJsonObject.get("condition");
				valueCondition = (String) myJsonObject.get("valueContition,,");
				columnConditionValue = this.getColumnValue(columnCondition);
				
				myDataBase.deleteImagebyCondition(columnConditionValue, valueCondition);
				return toSend=myJson.serializeConfirmation(true);
			case 5: //INSERT
				String name = myJson.getName(message);
				String author = myJson.getAuthor(message);
				String year = Integer.toString(myJson.getYear(message));
				String size = Integer.toString(myJson.getSize(message));
				String description = myJson.getDescription(message);
				
				myDataBase.addImage(name, author, year, size, description);
				toSend=myJson.serializeConfirmation(true);
				
				return toSend;
			case 15: //OPEN IMAGE BUTTON
				
				

				return toSend;
			case 16: //DELETE IMAGE BUTTON
				int id = myJson.getId(message);
				myDataBase.deleteImage(id);

				return toSend=myJson.serializeConfirmation(true);
			default:
				break;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public int getColumnValue(String column) {
		switch (column) {
		case "name":
			return 1;
		case "author":
			return 2;
		case "year":
			return 3;
		case "size":
			return 4;
		case "descrption":
			return 5;

		default:
			return 0;
		}
	}

}
