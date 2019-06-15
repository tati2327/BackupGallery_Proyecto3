package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.serverManage.clientManager;
import com.serverManage.dataBaseManager;
import com.serverManage.raidManager;

public class ManageClient extends Thread {
	
	Socket client = null;
	String name = "";
	String message = "";

	DataInputStream in = null;
	DataOutputStream out = null;
	ServerJson myJson = new ServerJson();
	
	int request = 0;
	
	clientManager myClientManager = new clientManager();
	dataBaseManager myDataBaseManager = new dataBaseManager();
	raidManager myRaidManager = new raidManager();
	
	public  ManageClient(Socket myClient, DataInputStream in, DataOutputStream out) {
		this.client = myClient;
		this.in = in;
		this.out = out;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		System.out.println("Nuevo cliente conectado");
		while(true) {
			try {

				System.out.println("Voy a comenzar a recibir el mensaje..");
				while(true) {
					message = in.readLine();
					System.out.println(message);

				}
				
				//out.writeUTF("Servidor");
				
				

				/*request = myJson.getRequest(message);

			//Configurar el nombre
			if(request==1) {
				this.name = myJson.getName(message);
				System.out.println("Soy el cliente "+name);
			}

			//----------Segun el tipo de solicitud el mensaje se manda donde un manager--------------/

			if(request >=2  || request<=5) {
				//Manage del cliente
				myClientManager.readMessage(message);
			}
			if(request == 6) {
				//Manage para el Raid
				myRaidManager.readMessage(message);
			}
			if(request >=7  || request<=9 || request == 14) {
				//Manage de la base de datos
				myDataBaseManager.readMessage(message);
			}*/
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
