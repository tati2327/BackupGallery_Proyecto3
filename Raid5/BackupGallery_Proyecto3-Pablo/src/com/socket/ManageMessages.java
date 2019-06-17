package com.socket;

public class ManageMessages {
	
	String toSend=null;
	ClientJson myJson = new ClientJson();
	BaseN

	/**
	 * Lee el mensaje que llega del servidor y segun sea la 
	 * solicitud retorna un mensaje de respuesta
	 * @param message
	 * @return
	 */
	public String readMessage(String message) {
		int request=myJson.getRequest(message);
		
		switch (request) {
		case 2: //SELECT
			//myDataBase.selectBy(request, retur, thing)
			
			return toSend;
		case 3: //UPDATE
			
			return toSend;
		case 4: //DELETE
			
			return toSend;
		case 5: //INSERT
			
			return toSend;
		case 15: //OPEN IMAGE BUTTON
			
			return toSend;
		case 16: //DELETE IMAGE BUTTON
			
			return toSend;

		default:
			break;
		}
		
		
		return null;
		
	}

}
