package com.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import com.database.BaseNoSql;

public class Client {
	
	static final String HOST = "127.0.0.1";
	static final int PUERTO = 54000;
	Socket mySocket=new Socket();
	ManageMessages myManage= new ManageMessages();
	ClientJson myJson =  new ClientJson();
	Boolean init = true;
	
	String message="", toSend="";
	
	public void newClient() throws InterruptedException {
		try {
			Socket socket = new Socket(HOST, PUERTO);
			
			DataInputStream in = new DataInputStream(socket.getInputStream()); //Recibir mensaje
			DataOutputStream out = new DataOutputStream(socket.getOutputStream()); //Enviar mensaje
			
			while(true) {
				if(init) {
					Thread.sleep (1000);
					toSend=myJson.serializeName("dataBase");
					out.writeUTF(toSend);
					System.out.println("DATABSE: "+toSend);
					init = false;
				}
				
				message = in.readUTF();
				System.out.println("SERVER: "+message);
				Thread.sleep (1000);
				toSend = myManage.readMessage(message);
				System.out.println("DATABASE: "+message);
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
	
}
