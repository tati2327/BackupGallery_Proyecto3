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
	
	DataInputStream in;
	DataOutputStream out;
	String message;
	Boolean init = false;
	
	public void newClient() {
		try {
			Socket socket = new Socket(HOST, PUERTO);
			
			in = new DataInputStream(socket.getInputStream()); //Recibir mensaje
			out = new DataOutputStream(socket.getOutputStream()); //Enviar mensaje
			
			while(true) {
				
				
				/*if(!init) {
					out.writeUTF("Nombre"); //Primero manda en nombre para identificarlo
				}
				if(message == "exit") {
					socket.close();
				}*/
				
				//Funciones que lean lo que llega del servidor
				
				
				out.writeUTF("Me he conectado zorro"); 
				System.out.println(in.readUTF());

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
