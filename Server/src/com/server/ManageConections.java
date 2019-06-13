package com.server;

import java.io.DataOutputStream;
import java.io.IOException;
import com.structures.SimpleList;

/*
 * Clase que manda los mensajes al respectivo socket
 */
public class ManageConections {

	SimpleList<ManageClients> myClients =  new SimpleList<ManageClients>();
	
	public void sendMessage(String name, String message) {
		int client = 0;
		
		//Se busca el socket del de a quien se le quiere mandar el mensaje
		for(int i=0; i<myClients.size(); i++) {
			if(myClients.get(i).name == name) {
				client = i;
			}
		}
		
		try {
			//Se envia el mensaje
			DataOutputStream out = new DataOutputStream(myClients.get(client).client.getOutputStream());
			out.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
