package com.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	static final String HOST = "127.0.0.1";
	static final int PUERTO = 54000;
	Socket mySocket;
	ManageMessages myManage;
	ClientJson myJson =  new ClientJson();
	
	DataInputStream in;
	DataOutputStream out;
	Boolean init = true;
	
	String message, toSend;
	
	public void newClient() {
		try {
			Socket socket = new Socket(HOST, PUERTO);
			
			in = new DataInputStream(socket.getInputStream()); //Recibir mensaje
			out = new DataOutputStream(socket.getOutputStream()); //Enviar mensaje
			
			while(true) {
				if(init) {
					out.writeUTF(myJson.serializeName("dataBase"));
					init = false;
				}
				
				message = in.readUTF();
				toSend = myManage.readMessage(message);
				out.writeUTF(toSend);
			}	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * La aplicación se corre desde aquí!!!!!!
	 * @param args
	 */
	public static void main(String[] args) {
		Client c = new Client();
		c.newClient();
	}
}
