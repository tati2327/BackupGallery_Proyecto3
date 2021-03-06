package com.server;

import java.io.DataOutputStream;
import java.io.IOException;
import com.structures.SimpleList;

/*
 * Clase que manda los mensajes al respectivo socket
 */
public class ManageConections {

	static SimpleList<ManageClient> myClients =  new SimpleList<ManageClient>();
	static byte[] image;
	static String doubleConfirmation="false";
	
	public String isDoubleConfirmation() {
		return doubleConfirmation;
	}

	public void setDoubleConfirmation(String doubleConfirmation) {
		this.doubleConfirmation = doubleConfirmation;
	}

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
			System.out.println("CLIENT "+myClients.get(client)+": "+message);
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveImages(byte[] image) {
		this.image = image;
	}

	public byte[] getImage() {
		return image;
	}
}